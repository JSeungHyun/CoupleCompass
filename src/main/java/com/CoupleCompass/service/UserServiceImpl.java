package com.CoupleCompass.service;

import com.CoupleCompass.dao.UserDao;
import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.util.CmFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) throws CmFunction.ValidationException {
        try {
            // 이름 확인
            String username = userDto.getUsername();
            if (!CmFunction.isValidUsername(username)) {
                throw new CmFunction.ValidationException("사용자 이름을 다시 확인해주세요.");
            }

            // 이메일 주소 확인
            String email = userDto.getEmail();
            if (!CmFunction.isValidEmail(email)) {
                throw new CmFunction.ValidationException("이메일 주소를 다시 확인해주세요.");
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);

            // 사용자 저장
            userDao.saveUser(userDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CmFunction.ValidationException("사용자 저장에 실패했습니다.");
        }
    }

    @Override
    public UserDto findByUserId() {
        return null;
    }
}
