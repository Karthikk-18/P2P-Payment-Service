package com.fintech.p2p_payment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private Long userId;
    private String username;
    private String email;
}
