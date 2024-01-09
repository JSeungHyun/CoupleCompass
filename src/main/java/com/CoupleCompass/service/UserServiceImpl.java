package com.CoupleCompass.service;

import com.CoupleCompass.dao.UserDao;
import com.CoupleCompass.dto.CoupleDto;
import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.util.CmFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.CoupleCompass.util.CmFunction.getClientIP;
import static com.CoupleCompass.util.CmFunction.setLoginSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /** 회원가입 */
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

            // 이메일 중복 확인
            if (findByEmail(email) != null) {
                throw new CmFunction.ValidationException("이미 가입된 회원입니다.");
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);

            // 초대 코드 검사
            String code = userDto.getCode();
            if (code.equals("") || code == null) { // 초대 코드가 없다면
                // 고유한 초대 코드 생성
                code = CmFunction.generateInviteCode();
                userDto.setCode(code);
                userDao.saveUser(userDto); // 사용자 저장

            } else if (findByCode(code) == null) { // 초대 코드가 있다면 db에서 조회
                throw new CmFunction.ValidationException("없는 초대 코드입니다. 다시 확인해주세요.");

            } else { // db에서 초대 코드를 찾았다면 커플 관계 맺기
                UserDto userdtoByCode = findByCode(code);

                Long user1Id = userdtoByCode.getUserId(); // 사용자 1 아이디
                Long user2Id = userDao.saveUser(userDto); // 사용자 2 아이디 (사용자 저장 후)

                CoupleDto coupleDto = CoupleDto.builder()
                        .user1Id(user1Id)
                        .user2Id(user2Id)
                        .build();
                saveCouple(coupleDto); // 커플 관계 저장

                // 초대 코드 폭파
                updateCodeToNull(user1Id);
                updateCodeToNull(user2Id);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CmFunction.ValidationException(e.getMessage());
        }
    }

    /**
     * 로그인 + 세션 저장
     */
    @Override
    public ResponseEntity<String> login(UserDto userDto, HttpServletRequest request) {
        try {
            String ip = getClientIP(request); // 접속한 IP
            System.out.println("ID => " + ip);

            UserDto userDtoByEmail = findByEmail(userDto.getEmail());
            if (userDtoByEmail != null) { // 이메일 일치
                String password = passwordEncoder.encode(userDto.getPassword());

                if (password == userDtoByEmail.getPassword()) { // 비밀번호 일치 => 로그인 성공
                    CoupleDto coupleDto = findCoupleByUserId(userDtoByEmail.getUserId()); // 커플 정보 가져오기
                    setLoginSession(userDto, coupleDto); // 세션 초기화
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
        } catch (Exception e) {
            System.out.println("아이디 혹은 비밀번호가 잘못됐습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 혹은 비밀번호가 잘못됐습니다.");
        }
        System.out.println("userId => " + userDto.getUserId());
        System.out.println("userEmail => " + userDto.getEmail());
        return ResponseEntity.ok("환영합니다!");
    }

    /** 이메일로 사용자 정보 찾기 */
    @Override
    public UserDto findByEmail(String email) {
        UserDto userDto = null;
        try {
            userDto = userDao.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userDto;
    }

    /** 초대 코드로 사용자 정보 찾기 */
    @Override
    public UserDto findByCode(String code) {
        UserDto userDto = null;
        try {
            userDto = userDao.findByCode(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userDto;
    }

    /** 커플 저장하기 */
    @Override
    public void saveCouple(CoupleDto coupleDto) {
        try {
            userDao.saveCouple(coupleDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** 초대 코드 값 null로 초기화 */
    @Override
    public void updateCodeToNull(Long userId) {
        try {
            userDao.updateCodeToNull(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** 사용자 아이디로 커플 정보 가져오기 */
    @Override
    public CoupleDto findCoupleByUserId(Long userId) {
        CoupleDto coupleDto = null;
        try {
            coupleDto = userDao.findCoupleByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return coupleDto;
    }
}
