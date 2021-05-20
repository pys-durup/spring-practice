package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
}
