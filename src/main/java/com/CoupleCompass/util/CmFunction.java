package com.CoupleCompass.util;

import com.CoupleCompass.dto.CoupleDto;
import com.CoupleCompass.dto.UserDto;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmFunction {

    /** HttpServletRequest 초기화 */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest hsr = sra.getRequest();
        return hsr;
    }

    /** 사용자 이메일 정규식 */
    public static boolean isValidEmail(String email) {
        if (email.equals("") || email == null) {
            return false;
        }
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    /** 사용자 이름 정규식 */
    public static boolean isValidUsername(String username) {
        final String USERNAME_PATTERN = "^[a-zA-Z0-9가-힣]*$";
        final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /** 에러 처리용 메서드 */
    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }

    /** 고유한 UUID 생성 */
    public static String generateInviteCode() {
        UUID uuid = UUID.randomUUID();
        String inviteCode = uuid.toString().replace("-", "");
        return inviteCode.substring(0, 8);
    }

    /**
     * 클라리언트 IP 구하기
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /** 로그인 후 세션 초기화 */
    public static void setLoginSession(UserDto userDto, CoupleDto coupleDto) {
        HttpSession session = getCurrentRequest().getSession();
        session.invalidate();
        session = CmFunction.getCurrentRequest().getSession();

        session.setAttribute("userId", userDto.getUserId()); // 사용자 아이디
        session.setAttribute("email", userDto.getEmail()); // 사용자 이메일
        session.setAttribute("coupleId", coupleDto.getCoupleId()); // 커플 아이디
    }
}
