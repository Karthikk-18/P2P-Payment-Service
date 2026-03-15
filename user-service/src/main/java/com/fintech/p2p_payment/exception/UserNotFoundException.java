package com.fintech.p2p_payment.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User not found with id : " + id);
    }
}
