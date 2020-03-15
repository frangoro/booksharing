package org.frangoro.booksharing.controller;

import org.frangoro.booksharing.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserDto userDto) {
        return "newUser";
    }

}
