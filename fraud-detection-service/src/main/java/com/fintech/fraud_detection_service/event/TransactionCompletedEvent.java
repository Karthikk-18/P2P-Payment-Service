package com.fintech.fraud_detection_service.event;

import lombok.*;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCompletedEvent {
    private Long transactionId;
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime timestamp;
}
