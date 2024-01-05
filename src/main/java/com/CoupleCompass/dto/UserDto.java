package com.CoupleCompass.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private String userId;
    private String email;
    private String username;
    private String password;
    private LocalDateTime createdAt;
}
