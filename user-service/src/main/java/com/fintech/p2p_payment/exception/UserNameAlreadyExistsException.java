package com.fintech.p2p_payment.exception;

public class UserNameAlreadyExistsException extends RuntimeException {
    public UserNameAlreadyExistsException(){
        super("UserName Already Exists");
    }
}
