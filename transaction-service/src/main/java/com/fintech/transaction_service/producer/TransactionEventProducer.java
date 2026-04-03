package com.fintech.transaction_service.producer;

import com.fintech.transaction_service.event.TransactionCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionEventProducer {

    private final KafkaTemplate<String, TransactionCompletedEvent> kafkaTemplate;

    public void publishTransactionEvent(com.fintech.transaction_service.event.TransactionCompletedEvent event) {
        kafkaTemplate.send("transaction-complete",event);
    }
}
