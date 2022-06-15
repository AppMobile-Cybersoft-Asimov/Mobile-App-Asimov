package com.example.asimov.data.model;

import androidx.annotation.NonNull;

public class LoginResponse {

    private int id;
    private String email;
    private String token;

    public int getId() {
        return id;
    }

    public LoginResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResponse setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
