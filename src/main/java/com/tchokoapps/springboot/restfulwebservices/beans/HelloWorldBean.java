package com.tchokoapps.springboot.restfulwebservices.beans;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
