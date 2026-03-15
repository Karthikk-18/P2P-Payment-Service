package com.fintech.wallet_service.client;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.naming.ServiceUnavailableException;

@Component
public class UserClientFallback implements UserClient{
    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
