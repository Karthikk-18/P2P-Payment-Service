package com.fintech.transaction_service.client;

import com.fintech.transaction_service.exceptions.ServiceUnavailableException;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public boolean existsById(Long id) {
        throw new ServiceUnavailableException("user-service is currently unavailable");
    }

    @Override
    public String getEmailById(Long id) {
        throw new ServiceUnavailableException("user-service is currently unavailable");
    }
}