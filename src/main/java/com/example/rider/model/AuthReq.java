package com.example.rider.model;

public class AuthReq {
    private String cred;
    private String password;

    public AuthReq() {}

    public AuthReq(String cred, String password) {
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
