package com.CoupleCompass.service;

import com.CoupleCompass.dto.CoupleDto;
import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.util.CmFunction;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    void saveUser(UserDto userDto) throws CmFunction.ValidationException;
    ResponseEntity<String> login(UserDto userDto, HttpServletRequest request);
    UserDto findByEmail(String email);
    UserDto findByCode(String code);
    void saveCouple(CoupleDto coupleDto);
    void updateCodeToNull(Long userId);
    CoupleDto findCoupleByUserId(Long userId);
}
