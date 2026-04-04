package com.fintech.notification_service.consumer;

import com.fintech.notification_service.event.TransactionCompletedEvent;
import com.fintech.notification_service.event.TransactionStatus;
import com.fintech.notification_service.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionEventConsumer {

    private final EmailNotificationService emailService;

    @KafkaListener(topics = "transaction-completed",
            groupId = "notification-group")
    public void consume(TransactionCompletedEvent event) {
        log.info("Received transaction event: id{}",event.getTransactionId());

        if(event.getStatus() == TransactionStatus.SUCCESS) {
            // notify sender
            emailService.sendTransactionalEmail(
                    event.getSenderEmail(),
                    "Transaction Successful",
                    "You Sent " + event.getAmount()
                            + " to User " + event.getReceiverId()
            );
            // notify receiver
            emailService.sendTransactionalEmail(
                    event.getReceiverEmail(),
                    "Money Received",
                    "You Received "+event.getAmount()
                            + " from user " + event.getSenderId()
            );
        } else if (event.getStatus() == TransactionStatus.FAILED) {
            // notify sender only
            emailService.sendTransactionalEmail(
                    event.getSenderEmail(),
                    "Transaction Failed",
                    "Your transaction of " + event.getAmount()
                            + " failed. If amount was deducted, a refund has been initiated"
            );
        }

        // Notify Receiver
        emailService.sendTransactionalEmail(
                event.getReceiverEmail(),
                "Money Received",
                "You Received "+event.getAmount()
                + " from user " + event.getSenderId()
        );
    }
}
