package com.tchokoapps.springboot.restfulwebservices.entities;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private Date birthDate;

    public User() {
    }

    public User(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
