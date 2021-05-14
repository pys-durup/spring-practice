package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;

public interface MemberRepository {

    Member save(Member member);
    Member edit(Long id, Member member);
    Member findById(Long id);
    void delete(Long id);
    void clear();
}
