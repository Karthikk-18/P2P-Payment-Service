package com.fintech.wallet_service.controller;

import com.fintech.wallet_service.dto.DepositRequestDto;
import com.fintech.wallet_service.dto.WalletRequestDto;
import com.fintech.wallet_service.dto.WalletResponseDto;
import com.fintech.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(
            @Valid @RequestBody WalletRequestDto requestDto
            ) {
        WalletResponseDto responseDto = walletService.createWallet(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable Long userId
    ) {
        BigDecimal balance = walletService.getBalance(userId);
        return ResponseEntity.ok().body(balance);
    }

    @PostMapping("/deposit/{userId}")
    public ResponseEntity<WalletResponseDto> deposit(
            @PathVariable Long userId,
            @RequestBody DepositRequestDto depositRequestDto
    ) {
        if(depositRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        WalletResponseDto responseDto = walletService.deposit(userId, depositRequestDto.getAmount());
        return ResponseEntity.ok().body(responseDto);
    }

}
