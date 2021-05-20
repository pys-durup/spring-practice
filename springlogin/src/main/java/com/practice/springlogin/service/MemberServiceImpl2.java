package com.practice.springlogin.service;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.model.loginDTO;
import com.practice.springlogin.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl2 implements MemberService{

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl2(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void signUp(Member member) {
        memberMapper.insertMember(member);
    }

    @Override
    public Member login(String id, String password) {
        loginDTO loginDTO = new loginDTO(id, password);
        return memberMapper.loginCheck(loginDTO);
    }

    @Override
    public Member myInfo(Long seq) {
        return memberMapper.findBySeq(seq);
    }

    @Override
    public void myInfoEdit(Member member) {

    }
}
