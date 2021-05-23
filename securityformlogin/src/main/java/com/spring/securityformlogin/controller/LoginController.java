package com.spring.securityformlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/main")
    public String main() {

        return "main";
    }

    @GetMapping("/login")
    public String loginForm() {

        return "loginPage";
    }
}
