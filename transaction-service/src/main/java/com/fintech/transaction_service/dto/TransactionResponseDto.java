package com.fintech.transaction_service.dto;

import com.fintech.transaction_service.entity.TransactionStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
   private Long transactionId;
   private Long senderId;
   private Long receiverId;
   private BigDecimal amount;
   private TransactionStatus status;
   private LocalDateTime createdAt;
}
