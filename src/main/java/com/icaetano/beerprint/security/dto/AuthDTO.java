package com.icaetano.beerprint.security.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO implements Serializable {
    private String username;

    private String password;

    private boolean rememberMe;



    @Override
    public String toString() {
        return "LoginDTO{" +
            "username='" + username + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}