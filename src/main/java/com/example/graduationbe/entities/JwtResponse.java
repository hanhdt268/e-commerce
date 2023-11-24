package com.example.graduationbe.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {
    String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
