package com.example.rider.service;

import com.example.rider.config.JwtTokenUtil;
import com.example.rider.exception.CustomException;
import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RiderService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Rider addRider (Rider rider) {
		if (rider.getEmail().isEmpty())
			rider.setEmail(null);
		rider.setPassword(passwordEncoder.encode(rider.getPassword()));
		return repo.save(rider);
	}

	public String authRider(String cred, String password) throws Exception {
		Rider r = repo.findByContact(cred).get();
		if (r != null && passwordEncoder.matches(password, r.getPassword()))
			return jwtTokenUtil.generateToken(r);
		throw new CustomException("Invalid credentials");
	}

	

}
