package com.CoupleCompass.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CoupleDto {
    private Long coupleId;
    private Long user1Id;
    private Long user2Id;
    private LocalDateTime createdAt;
}
