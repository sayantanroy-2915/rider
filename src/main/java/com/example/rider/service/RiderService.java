package com.example.rider.service;

import com.example.rider.exception.CustomException;
import com.example.rider.model.AuthReq;
import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import com.example.rider.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiderService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;

	public Rider addRider (Rider rider) {
		// rider.setPassword(passwordEncoder.encode(rider.getPassword()));
		return repo.save(rider);
	}

	public String authRider(AuthReq authReq) throws Exception {
		Optional<Rider> opr = repo.findByContact(authReq.getCred());
		if (opr.isPresent()) {
			Rider r = opr.get();
			if (passwordEncoder.matches(authReq.getPassword(), r.getPassword()))
				return jwtUtil.generateToken(r);
		}
		throw new CustomException("Invalid credentials: "+authReq.getCred());
	}

}
