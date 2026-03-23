package com.fintech.wallet_service.service;

import com.fintech.wallet_service.client.UserClient;
import com.fintech.wallet_service.dto.WalletRequestDto;
import com.fintech.wallet_service.dto.WalletResponseDto;
import com.fintech.wallet_service.entity.Wallet;
import com.fintech.wallet_service.exception.InsufficientBalanceException;
import com.fintech.wallet_service.exception.UserNotFoundException;
import com.fintech.wallet_service.exception.WalletAlreadyExistsException;
import com.fintech.wallet_service.exception.WalletNotFoundException;
import com.fintech.wallet_service.mapper.WalletMapper;
import com.fintech.wallet_service.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;
    private final UserClient userClient;

    @Transactional
    public WalletResponseDto createWallet(WalletRequestDto requestDto) {
          if(!userClient.existsById(requestDto.getUserId())) {
              throw new UserNotFoundException(requestDto.getUserId());
          }
          if(walletRepository.existsByUserId(requestDto.getUserId())) {
              throw new WalletAlreadyExistsException(requestDto.getUserId());
          }
          Wallet wallet1 = walletMapper.toEntity(requestDto);
          Wallet savedWallet = walletRepository.save(wallet1);
          return walletMapper.toDto(savedWallet);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletNotFoundException(userId));
        return wallet.getBalance();
    }

    @Transactional
    public WalletResponseDto deposit(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletNotFoundException(userId));
        if(wallet.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        wallet.setBalance(wallet.getBalance().add(amount));
        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.toDto(savedWallet   );
    }

    @Transactional
    public WalletResponseDto deduct(Long userId, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletNotFoundException(userId));
        if(wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.toDto(savedWallet);
    }
}
