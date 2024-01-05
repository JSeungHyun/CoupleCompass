package com.CoupleCompass;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionsDao {
    void insertRegions(List<InsertRegionsDto> regionsDtos);
}
