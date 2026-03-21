package com.fintech.transaction_service.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException() {
       super("Insufficient Balance ");
    }
}
