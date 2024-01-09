package com.CoupleCompass.dao;

import com.CoupleCompass.dto.CoupleDto;
import com.CoupleCompass.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    Long saveUser(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto findByCode(String code);
    void saveCouple(CoupleDto coupleDto);
    void updateCodeToNull(Long userId);
    CoupleDto findCoupleByUserId(Long userId);
}
