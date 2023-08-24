package com.example.rider.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

public class RiderRole implements GrantedAuthority {

    private final String authority;

    public RiderRole() {
        super();
        authority = "rider";
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
