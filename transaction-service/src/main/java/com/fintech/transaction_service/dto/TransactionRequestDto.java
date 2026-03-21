package com.fintech.transaction_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
    @NotNull(message = "senderId is required")
    private Long senderId;

    @NotNull(message = "receiver is required")
    private Long receiverId;

    @NotNull
    @DecimalMin(value = "1.00", message = "amount must be greater than zero")
    private BigDecimal amount;

}
