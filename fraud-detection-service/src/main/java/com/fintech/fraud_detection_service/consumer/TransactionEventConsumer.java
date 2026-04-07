package com.fintech.fraud_detection_service.consumer;

import com.fintech.fraud_detection_service.event.TransactionCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionEventConsumer {

    @KafkaListener(topics = "transaction-completed",
            groupId = "fraud-detection-group")
    public void consume(TransactionCompletedEvent event) {
        log.info("Received transaction event: id={} amount={}",
                event.getTransactionId(),
                event.getAmount());

        // Rule-1  Large amount
        if(event.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            log.warn("FRAUD ALERT: Large transaction detected. "+
                    "TransactionId={} Amount={} SenderId={}",
                    event.getTransactionId(),
                    event.getAmount(),
                    event.getSenderId());
        }

        // Rule-2  unusual Time
        int hour = event.getTimestamp().getHour();
        if(hour >= 23 || hour < 5) {
            log.warn("FRAUD ALERT: Unusual Time transaction "+
                            "TransactionId={} Time={} SenderId={}",
                    event.getTransactionId(),
                    event.getTimestamp(),
                    event.getSenderId());
        }

        log.info("Fraud Check completed for transactionId={}",
                event.getTransactionId());
    }
}
