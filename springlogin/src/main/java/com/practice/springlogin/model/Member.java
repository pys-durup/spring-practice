package com.practice.springlogin.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long seq;
    private String id;
    private String password;
    private String nickname;

    public Member(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }
}
