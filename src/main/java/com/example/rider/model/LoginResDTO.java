package com.example.rider.model;

public class LoginResDTO {
    private RiderDetails rider;
    private String jwt;

    public LoginResDTO() {
        super();
    }

    public LoginResDTO(RiderDetails rider, String jwt) {
        this.rider = rider;
        this.jwt = jwt;
    }

    public RiderDetails getRider() {
        return rider;
    }

    public void setRider(RiderDetails rider) {
        this.rider = rider;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
