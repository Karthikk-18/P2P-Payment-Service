package com.fintech.wallet_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDto {
    private Long walletId;
    private Long userId;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
