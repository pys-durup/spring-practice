package com.practice.springlogin.service;

import com.practice.springlogin.model.Member;

public interface MemberService {

    void signUp(Member member);
    Member login(String id, String password);
    Member myInfo(Long seq);
    void myInfoEdit(Member member);

}
