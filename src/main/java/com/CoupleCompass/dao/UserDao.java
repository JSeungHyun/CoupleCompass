package com.CoupleCompass.dao;

import com.CoupleCompass.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    void saveUser(UserDto userDto);
}
