package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setSeq(++sequence);
        store.put(member.getSeq(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member member = store.get(id);
        return member;
    }

    @Override
    public Member edit(Long id, Member member) {
        store.put(member.getSeq(), member);
        return member;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clear() {
        store.clear();
    }
}
