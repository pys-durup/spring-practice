package com.practice.springlogin.controller;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.service.MemberService;
import com.practice.springlogin.service.MemberServiceImpl;
import com.practice.springlogin.service.MemberServiceImpl2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/account")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberServiceImpl2 memberService) {
        this.memberService = memberService;
    }

    //메인페이지
    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();


        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie -> " + cookie.getName() + " : " + cookie.getValue());
        }

        if(session.getAttribute("member") != null) {
            Member member = (Member) session.getAttribute("member");
            model.addAttribute("member", memberService.myInfo(member.getSeq()));
        }
        return "member/index";
    }


    //회원가입 폼
    @GetMapping("signup")
    public String loginForm() {

        return "member/signupForm";
    }

    //회원 가입 로직
    @PostMapping("signup")
    public String signup(@ModelAttribute("member") Member member) {
        memberService.signUp(member);
        return "redirect:/member/account";
    }

    //로그아웃 로직
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "member/index";
    }

    //로그인 로직
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

    //로그인 폼
    @GetMapping("auth")
    public String signupForm() {

        return "member/loginForm";
    }

    //내정보
    @GetMapping("/{seq}")
    public String myInfo(@PathVariable Long seq, Model model) {
        Member member = memberService.myInfo(seq);

        model.addAttribute("member", member);

        return "member/myInfo";
    }

    //내정보 수정 폼
    @GetMapping("/{seq}/edit")
    public String myInfoEditForm(@PathVariable Long seq, Model model) {
        Member member = memberService.myInfo(seq);

        model.addAttribute("member", member);

        return "member/myInfoEditForm";
    }

    //내정보 수정 로직
    @PostMapping("/{seq}/edit")
    public String myInfoEdit(@PathVariable Long seq,
                             @ModelAttribute Member member) {

        memberService.myInfoEdit(member);

        return "redirect:/member/account/{seq}";
    }










}
