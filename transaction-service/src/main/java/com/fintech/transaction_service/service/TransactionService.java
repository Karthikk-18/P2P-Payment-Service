package com.fintech.transaction_service.service;

import com.fintech.transaction_service.client.UserClient;
import com.fintech.transaction_service.client.WalletClient;
import com.fintech.transaction_service.dto.AmountRequestDto;
import com.fintech.transaction_service.dto.TransactionRequestDto;
import com.fintech.transaction_service.dto.TransactionResponseDto;
import com.fintech.transaction_service.entity.Transaction;
import com.fintech.transaction_service.entity.TransactionStatus;
import com.fintech.transaction_service.exceptions.*;
import com.fintech.transaction_service.mapper.TransactionMapper;
import com.fintech.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserClient userClient;
    private final WalletClient walletClient;

    @Transactional
    public TransactionResponseDto sendMoney(TransactionRequestDto requestDto) {
        if(!userClient.existsById(requestDto.getSenderId())) {
            throw new UserNotFoundException(requestDto.getSenderId());
        }
        if(!userClient.existsById(requestDto.getReceiverId())) {
            throw new UserNotFoundException(requestDto.getReceiverId());
        }
        if(Objects.equals(requestDto.getSenderId(), requestDto.getReceiverId())) {
            throw new IllegalArgumentException("Sender and Receiver must be different");
        }
        Transaction transaction = transactionMapper.toEntity(requestDto);
        transaction.setStatus(TransactionStatus.PENDING);

        BigDecimal senderBalance = walletClient.getBalance(requestDto.getSenderId());

        if(senderBalance.compareTo(requestDto.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        Transaction savedTransaction = transactionRepository.save(transaction);

        try {
            walletClient.deduct(requestDto.getSenderId(), new AmountRequestDto(requestDto.getAmount()));
            walletClient.deposit(requestDto.getReceiverId(), new AmountRequestDto(requestDto.getAmount()));
            savedTransaction.setStatus(TransactionStatus.SUCCESS);
        } catch (Exception ex) {
            try {
                refundSender(requestDto.getSenderId(), requestDto.getAmount());
            } catch (Exception refundEx) {
                savedTransaction.setStatus(TransactionStatus.FAILED);
                transactionRepository.save(savedTransaction);
                throw new TransactionFailedException(
                        "CRITICAL: Transfer failed and refund unsuccessful. Manual Review Required for Transaction"
                );
            }
            savedTransaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(savedTransaction);
            throw new TransactionFailedException();
        }
        return transactionMapper.toDto(transactionRepository.save(savedTransaction));
    }

    private void refundSender(Long senderId, BigDecimal amount) {
        walletClient.deposit(senderId, new AmountRequestDto(amount));
    }

    @Transactional(readOnly = true)
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return transactionMapper.toDto(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponseDto> getTransactionHistory(Long userId) {
        List<Transaction> transactions = transactionRepository.findBySenderIdOrReceiverId(userId, userId);

        return transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }
}
