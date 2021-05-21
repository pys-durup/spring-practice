package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;
import com.practice.springlogin.model.loginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    Member findBySeq(Long seq);
    Member findById(String id);
    Member loginCheck(loginDTO loginDTO);
    void myInfoEdit(Member member);
}
