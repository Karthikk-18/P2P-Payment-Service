package com.fintech.wallet_service.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException () {
        super("Insufficient Balance ");
    }
}
