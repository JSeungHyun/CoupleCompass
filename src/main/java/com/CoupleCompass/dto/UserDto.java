package com.CoupleCompass.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String code;
    private LocalDateTime createdAt;
}
