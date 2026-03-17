package com.fintech.wallet_service.exception;

public class WalletAlreadyExistsException extends RuntimeException{
    public WalletAlreadyExistsException(Long userId){
        super("Wallet Already Exists for user Id : "+userId);
    }
}
