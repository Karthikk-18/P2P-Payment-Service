package com.fintech.p2p_payment.mapper;

import com.fintech.p2p_payment.dto.UserRegisterRequestDto;
import com.fintech.p2p_payment.dto.UserResponseDto;
import com.fintech.p2p_payment.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

   public UserResponseDto toDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
   }

   public User toEntity(UserRegisterRequestDto requestDto, String encodedPassword){
       return User.builder()
               .username(requestDto.getUsername())
               .email(requestDto.getEmail())
               .passwordHash(encodedPassword)
               .build();
   }
}
