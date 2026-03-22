package com.fintech.transaction_service.exceptions;

public class TransactionFailedException extends RuntimeException {
    public TransactionFailedException() {
        super("Transaction failed sender has refunded. ");
    }
}
