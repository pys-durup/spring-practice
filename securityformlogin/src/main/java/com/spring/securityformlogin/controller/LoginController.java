package com.spring.securityformlogin.controller;

import com.spring.securityformlogin.domain.UserEntity;
import com.spring.securityformlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/main")
    public String main() {

        return "main";
    }

    @GetMapping("/login")
    public String loginForm() {

        return "loginPage";
    }

    @GetMapping("/signUp")
    public String signUp() {
        System.out.println("LoginController.signUp");
        UserEntity user = UserEntity.builder()
                .name("durup")
                .password(passwordEncoder.encode("1234"))
                .role("user")
                .build();

        userRepository.save(user);

        return "redirect:/login";
    }


}
