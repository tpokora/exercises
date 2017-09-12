package com.tpokora.exercises.auth.domain;

public class Profile {

    private String token;
    private String email;

    public Profile() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
