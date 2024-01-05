package com.CoupleCompass.dao;

import com.CoupleCompass.dto.RegionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegionDao {
    void insertRegions(List<RegionDto> regionDtos);
    List<RegionDto> selectRegion();
    void updateGeo(@Param("city") String city, @Param("gu") String gu, @Param("longitude") String longitude, @Param("latitude") String latitude);

}
