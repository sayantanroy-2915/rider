package com.example.rider.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class RiderRole implements GrantedAuthority {

/*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;
*/

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
