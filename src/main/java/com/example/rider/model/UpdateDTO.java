package com.example.rider.model;

/**
 * Authorized Update
 * Used to update Rider's attributes
 */
public class UpdateDTO {

    private Rider rider;
    private String oldPassword;
    private String jwt;

    public Rider getRider() {
        return rider;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "UpdateDTO{" +
                "rider=" + rider +
                ", oldPassword='" + oldPassword + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
