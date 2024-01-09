package com.CoupleCompass.controller;

import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.service.UserService;
import com.CoupleCompass.util.CmFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login_page() {
        return "login/login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login_action(UserDto userDto, HttpServletRequest request) {
        try {
            userService.login(userDto, request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("환영합니다!");
    }

    @GetMapping("/signup")
    public String signup_page() {
        return "login/signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup_action(UserDto userDto) throws CmFunction.ValidationException {
         try {
             userService.saveUser(userDto);
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         }
         return ResponseEntity.ok(userDto.getUsername() + " 님, 환영합니다!");
    }
}
