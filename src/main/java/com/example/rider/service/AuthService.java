package com.example.rider.service;

import com.example.rider.model.Rider;
import com.example.rider.model.RiderRegDTO;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private RiderRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Rider register(RiderRegDTO rider) {
        rider.setPassword(passwordEncoder.encode(rider.getPassword()));
        return repo.save(new Rider(rider));
    }
}
