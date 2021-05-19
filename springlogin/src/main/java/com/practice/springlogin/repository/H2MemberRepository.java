package com.practice.springlogin.repository;

import com.practice.springlogin.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class H2MemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public H2MemberRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("seq");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("password", member.getPassword());
        parameters.put("nickname", member.getNickname());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setSeq(key.longValue());
        return member;
    }

    @Override
    public Member edit(Long id, Member member) {
        return null;
    }

    @Override
    public Member findBySeq(Long id) {
        return null;
    }

    @Override
    public List<Member> list() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void clear() {

    }
}
