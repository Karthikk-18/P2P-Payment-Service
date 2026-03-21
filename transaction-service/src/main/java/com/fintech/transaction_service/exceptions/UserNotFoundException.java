package com.fintech.transaction_service.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User Not Found with id : "+id);
    }
}
