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
public class AmountRequestDto {
    @NotNull
    @DecimalMin(value = "1.00", message = "Amount must be greater than zero")
    private BigDecimal amount;
}
