package com.practice.springlogin.controller;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.service.MemberService;
import com.practice.springlogin.service.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "member/index";
    }

    @PostMapping("auth")
    public String signupForm(@RequestParam String id,
                             @RequestParam String password,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession();
        Member loginMember = memberService.login(id, password);

        if (loginMember == null) {
            session.setAttribute("member", null);
            redirectAttributes.addFlashAttribute("msg", "로그인 실패");
        } else {
            session.setAttribute("member", loginMember);
        }
        return "redirect:/member/account";
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
