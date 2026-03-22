package com.fintech.transaction_service.exceptions;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException (Long id) {
        super("Transaction are not found with id : "+id);
    }
}
