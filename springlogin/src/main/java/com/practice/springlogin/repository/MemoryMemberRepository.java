package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Member findBySeq(Long seq) {
        Member member = store.get(seq);
        return member;
    }

    @Override
    public Member edit(Long seq, Member member) {
        store.put(seq, member);
        return member;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }


    @Override
    public List<Member> list() {
        return new ArrayList<>(store.values());
    }


    @Override
    public void clear() {
        store.clear();
    }
}
