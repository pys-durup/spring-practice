package com.practice.springlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/account")
public class MemberController {

    @GetMapping
    public String index() {

        return "member/index";
    }

    @GetMapping("signup")
    public String loginForm() {

        return "member/loginForm";
    }

    @GetMapping("auth")
    public String signupForm() {

        return "member/signupForm";
    }
}
