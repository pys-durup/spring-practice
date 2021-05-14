package com.practice.springlogin.controller;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.service.MemberService;
import com.practice.springlogin.service.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/account")
public class MemberController {

    private final MemberService memberService = new MemberServiceImpl();

    @GetMapping
    public String index() {

        return "member/index";
    }

    @GetMapping("signup")
    public String loginForm() {

        return "member/signupForm";
    }

    @PostMapping("auth")
    public String signupForm(@RequestParam String id,
                             @RequestParam String password,
                             HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member checked = memberService.login(id, password);

        if (checked == null) {
            return "xxx";
        } else {
            return "member/index";
        }
    }

    @GetMapping("auth")
    public String signupForm() {

        return "member/loginForm";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute("member") Member member) {
        memberService.signUp(member);
        return "redirect:/member/account";
    }


}
