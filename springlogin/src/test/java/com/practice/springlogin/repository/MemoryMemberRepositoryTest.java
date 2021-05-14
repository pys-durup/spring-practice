package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;




class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        //given
        Member member = new Member("test@test.com", "1234", "test");

        //when
        Member savedMember = repository.save(member);

        //then
        Assertions.assertThat(member).isEqualTo(savedMember);

    }

    @Test
    public void edit() {
        //given
        Member member = new Member("test@test.com", "1234", "test");
        Member Member2 = new Member("test1@test.com", "1234", "test1");
        repository.save(member);

        //when
        Member editMember = repository.edit(member.getSeq(), Member2);

        //then
        Assertions.assertThat(editMember.getId()).isEqualTo(Member2.getId());
        Assertions.assertThat(editMember.getNickname()).isEqualTo(Member2.getNickname());
        Assertions.assertThat(editMember.getPassword()).isEqualTo(Member2.getPassword());
    }

    @Test
    public void delete() {
        //given
        Member member1 = new Member("test1@test.com", "1234", "test1");
        Member Member2 = new Member("test2@test.com", "1234", "test2");
        Member savedMember1 = repository.save(member1);
        Member savedMember2 = repository.save(Member2);

        //when
        repository.delete(savedMember1.getSeq());

        //then
        Assertions.assertThat(repository.findById(savedMember1.getSeq())).isEqualTo(null);

    }
}