package com.CoupleCompass.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    /** 에러 처리 */
    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
