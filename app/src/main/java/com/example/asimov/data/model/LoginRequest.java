package com.example.asimov.data.model;

public class LoginRequest {

    private final String email;
    private final String password;

    public LoginRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
