package com.fintech.wallet_service.exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(Long id) {
        super("Wallet Not found with id : "+id);
    }
}
