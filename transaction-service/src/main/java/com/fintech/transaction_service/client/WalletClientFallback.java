package com.fintech.transaction_service.client;

import com.fintech.transaction_service.dto.AmountRequestDto;
import com.fintech.transaction_service.exceptions.ServiceUnavailableException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletClientFallback implements WalletClient{
    @Override
    public BigDecimal getBalance(Long userId) {
        throw new ServiceUnavailableException("wallet-service is unavailable");
    }

    @Override
    public void deduct(Long userId, AmountRequestDto requestDto) {
        throw new ServiceUnavailableException("wallet service is unavailable");
    }

    @Override
    public void deposit(Long userId, AmountRequestDto requestDto) {
        throw new ServiceUnavailableException("wallet service is unavailable");
    }
}
