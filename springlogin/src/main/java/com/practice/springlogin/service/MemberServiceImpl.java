package com.practice.springlogin.service;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.repository.H2MemberRepository;
import com.practice.springlogin.repository.MemberRepository;
import com.practice.springlogin.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;
//    private final MemberRepository repository;
//    private final DataSource dataSource;

//    public MemberServiceImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.repository = new H2MemberRepository(dataSource);
//    }
    @Autowired
    public MemberServiceImpl() {
        this.repository = new MemoryMemberRepository();
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

    @Override
    public Member myInfo(Long seq) {
        Member member = repository.findBySeq(seq);
        return member;
    }

    @Override
    public void myInfoEdit(Member member) {
        repository.edit(member.getSeq(), member);
    }
}
