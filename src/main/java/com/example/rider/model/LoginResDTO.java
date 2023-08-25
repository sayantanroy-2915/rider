package com.example.rider.model;

public class LoginResDTO {
    private Rider rider;
    private String jwt;

    public LoginResDTO() {
        super();
    }

    public LoginResDTO(Rider rider, String jwt) {
        this.rider = rider;
        this.jwt = jwt;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
