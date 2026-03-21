package com.fintech.transaction_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "wallet-service", url = "${wallet-service.url}", fallback = WalletClientFallback.class)
public interface WalletClient {
    @GetMapping("/api/wallets/balance/{userId}")
   BigDecimal getBalance(@PathVariable("userId") Long userId);

    @PostMapping("/api/wallets/deduct/{userId}")
    void deduct(@PathVariable("userId") Long userId,
                @RequestBody BigDecimal amount);

    @PostMapping("/api/wallets/deposit/{userId}")
    void deposit(@PathVariable("userId") Long userId,
                 @RequestBody BigDecimal amount);
}
