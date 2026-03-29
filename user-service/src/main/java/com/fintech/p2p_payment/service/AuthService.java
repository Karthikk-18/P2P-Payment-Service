package com.fintech.p2p_payment.service;

import com.fintech.p2p_payment.dto.AuthResponseDto;
import com.fintech.p2p_payment.dto.LoginRequestDto;
import com.fintech.p2p_payment.entity.User;
import com.fintech.p2p_payment.repository.UserRepository;
import com.fintech.p2p_payment.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponseDto login(LoginRequestDto requestDto) {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          requestDto.getEmail(),
                          requestDto.getPassword()
                  )
          );

        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        return AuthResponseDto.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
