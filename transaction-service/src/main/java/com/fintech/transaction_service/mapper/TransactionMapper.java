package com.fintech.transaction_service.mapper;

import com.fintech.transaction_service.dto.TransactionRequestDto;
import com.fintech.transaction_service.dto.TransactionResponseDto;
import com.fintech.transaction_service.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequestDto requestDto) {
       return Transaction.builder()
               .senderId(requestDto.getSenderId())
               .receiverId(requestDto.getReceiverId())
               .amount(requestDto.getAmount())
               .build();
    }

    public TransactionResponseDto toDto(Transaction transaction) {
       return TransactionResponseDto.builder()
               .transactionId(transaction.getTransactionId())
               .senderId(transaction.getSenderId())
               .receiverId(transaction.getReceiverId())
               .amount(transaction.getAmount())
               .createdAt(transaction.getCreatedAt())
               .status(transaction.getStatus())
               .build();
    }
}
