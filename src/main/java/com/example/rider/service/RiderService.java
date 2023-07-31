package com.example.rider.service;

import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RiderService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Rider addRider (Rider rider) throws Exception {
		rider.setPassword(passwordEncoder.encode(rider.getPassword()));
		repo.save(rider);
		return rider;
	}

}
