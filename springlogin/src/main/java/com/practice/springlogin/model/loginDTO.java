package com.practice.springlogin.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class loginDTO {
    private String id;
    private String password;

    public loginDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
