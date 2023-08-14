package com.example.rider.model;

/**
 * Authorized Update
 * Used to update Rider's attributes
 */
public class AuthUpdate {

    private String field;
    private Object value;
    private String jwt;

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getJwt() {
        return jwt;
    }
}
