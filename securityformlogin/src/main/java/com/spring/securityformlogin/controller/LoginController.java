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

    @PostMapping("/doLogin")
    public String login() {
        // 로그인 검증 로직 - 성공
        return "redirect:/main";

        // 로그인 검증 로직 - 실패

    }
}
