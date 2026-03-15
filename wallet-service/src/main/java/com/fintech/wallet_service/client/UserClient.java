package com.fintech.wallet_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.ServiceUnavailableException;

@FeignClient(name = "user-service", url = "${user-service.url}", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/api/users/{id}/exists")
    public boolean existsById(@PathVariable Long id);
}

