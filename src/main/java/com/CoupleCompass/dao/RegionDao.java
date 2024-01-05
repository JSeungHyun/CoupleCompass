package com.CoupleCompass.dao;

import com.CoupleCompass.dto.RegionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionDao {
    void insertRegions(List<RegionDto> regionsDtos);
}
