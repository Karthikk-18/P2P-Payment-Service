package com.fintech.notification_service.event;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCompletedEvent {
    private Long transactionId;
    private Long senderId;
    private Long receiverId;
    private String senderEmail;
    private String receiverEmail;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime timestamp;
}