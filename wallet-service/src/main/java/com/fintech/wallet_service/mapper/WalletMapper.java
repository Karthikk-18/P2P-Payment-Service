package com.fintech.wallet_service.mapper;

import com.fintech.wallet_service.dto.WalletRequestDto;
import com.fintech.wallet_service.dto.WalletResponseDto;
import com.fintech.wallet_service.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {
    public WalletResponseDto toDto(Wallet wallet) {
        return WalletResponseDto.builder()
                .walletId(wallet.getWalletId())
                .userId(wallet.getUserId())
                .balance(wallet.getBalance())
                .createdAt(wallet.getCreatedAt())
                .build();
    }

    public Wallet toEntity (WalletRequestDto requestDto) {
        return Wallet.builder()
                .userId(requestDto.getUserId())
                .build();
    }
}
