package com.fintech.p2p_payment.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email Already Exists");
    }
}
