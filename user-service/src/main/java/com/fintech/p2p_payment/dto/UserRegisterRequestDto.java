package com.fintech.p2p_payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {

    @NotBlank(message = "Enter username")
    private String username;

    @Email
    @NotBlank(message = "Enter email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
