package com.fintech.wallet_service.client;

import com.fintech.wallet_service.exception.ServiceUnavailableException;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient{
    @Override
    public boolean existsById(Long id) {
        throw new ServiceUnavailableException("User-service is currently unavailable");
    }
}
