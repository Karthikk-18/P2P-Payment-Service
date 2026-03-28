package com.fintech.p2p_payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @Email(message = "Valid Email is required")
    @NotBlank(message = "email must be required")
    private String email;

    @NotBlank(message = "Password must be required")
    private String password;
}
