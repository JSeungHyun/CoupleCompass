package com.CoupleCompass;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertRegionsDto {
    private Long regionId;
    private String city;
    private String cityCode;
    private String gu;
    private String guCode;
}
