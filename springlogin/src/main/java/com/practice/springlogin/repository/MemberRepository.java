package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);
    Member edit(Long id, Member member);
    Member findBySeq(Long id);
    List<Member> list();
    void delete(Long id);
    void clear();
}
