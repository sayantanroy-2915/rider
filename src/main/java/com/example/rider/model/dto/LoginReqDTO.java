package com.example.rider.model.dto;

public class LoginReqDTO {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "LoginReqDTO { cred: " + username + "; password: " + password + " }";
    }

    public LoginReqDTO() {}

    public LoginReqDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCred() {
        return username;
    }
}
