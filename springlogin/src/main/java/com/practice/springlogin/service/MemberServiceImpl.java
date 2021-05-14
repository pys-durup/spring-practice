package com.practice.springlogin.service;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.repository.MemberRepository;
import com.practice.springlogin.repository.MemoryMemberRepository;

import java.util.List;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository = new MemoryMemberRepository();

    public MemberServiceImpl() {
        Member member = new Member("test", "1234", "test");
        repository.save(member);
    }

    @Override
    public void signUp(Member member) {
        repository.save(member);
    }

    @Override
    public Member login(String id, String password) {
        List<Member> members = repository.list();
        for (Member member : members) {
            if (member.getId().equals(id) && member.getPassword().equals(password)) {
                System.out.println("member.getId() 로그인 성공 = " + member.getId());
                return repository.findBySeq(member.getSeq());
            }
        }

        return null;
    }
}
