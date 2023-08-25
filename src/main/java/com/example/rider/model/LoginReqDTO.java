package com.example.rider.model;

public class LoginReqDTO {
    private String cred;
    private String password;

    public LoginReqDTO() {}

    public LoginReqDTO(String cred, String password) {
        this.cred = cred;
        this.password = password;
    }

    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
