package com.CoupleCompass.service;

import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.util.CmFunction;

public interface UserService {
    void saveUser(UserDto userDto) throws CmFunction.ValidationException;
    UserDto findByUserId();
}
