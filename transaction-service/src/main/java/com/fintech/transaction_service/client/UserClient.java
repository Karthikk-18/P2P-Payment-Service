package com.fintech.transaction_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/api/users/{id}/exists")
    boolean existsById(@PathVariable Long id);
}
