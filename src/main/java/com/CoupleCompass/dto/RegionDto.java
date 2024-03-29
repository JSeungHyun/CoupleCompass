package com.CoupleCompass.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionDto {
    private Long regionId;
    private String city;
    private String cityCode;
    private String gu;
    private String guCode;
}