package com.CoupleCompass.controller;

import com.CoupleCompass.dto.UserDto;
import com.CoupleCompass.service.UserService;
import com.CoupleCompass.util.CmFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login_page() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login_action(UserDto userDto) {
        System.out.println(userDto);
        return "login/login";
    }

    @GetMapping("/signup")
    public String signup_page() {
        return "login/signup";
    }

    @PostMapping("/signup")
    public String signup_action(UserDto userDto) throws CmFunction.ValidationException {
         try {
             userService.saveUser(userDto);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
        return "login/signup";
    }
}
