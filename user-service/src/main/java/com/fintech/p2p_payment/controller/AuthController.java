package com.fintech.p2p_payment.controller;

import com.fintech.p2p_payment.dto.AuthResponseDto;
import com.fintech.p2p_payment.dto.LoginRequestDto;
import com.fintech.p2p_payment.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody LoginRequestDto requestDto
            ) {
        AuthResponseDto responseDto = authService.login(requestDto);

        return ResponseEntity.ok(responseDto);
    }
}
