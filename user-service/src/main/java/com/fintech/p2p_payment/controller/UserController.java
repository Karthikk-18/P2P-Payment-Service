package com.fintech.p2p_payment.controller;

import com.fintech.p2p_payment.dto.UserRegisterRequestDto;
import com.fintech.p2p_payment.dto.UserResponseDto;
import com.fintech.p2p_payment.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterRequestDto requestDto
            ) {
        UserResponseDto responseDto = userService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable Long userId
    ) {
       UserResponseDto responseDto = userService.getUserById(userId);
       return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.existsById(id));
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<String> getEmailById(@PathVariable Long id) {
         String email = userService.getEmailId(id);
         return ResponseEntity.ok(email);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
       userService.deleteUser(userId);
       return ResponseEntity.noContent().build();
    }
}
