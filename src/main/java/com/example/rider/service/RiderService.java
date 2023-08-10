package com.example.rider.service;

import com.example.rider.config.JwtTokenUtil;
import com.example.rider.exception.CustomException;
import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiderService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Rider addRider (Rider rider) {
		// rider.setPassword(passwordEncoder.encode(rider.getPassword()));
		return repo.save(rider);
	}

	public String authRider(String cred, String password) throws Exception {
		Optional<Rider> opr = repo.findByContact(cred);
		if (opr.isPresent()) {
			Rider r = opr.get();
			System.out.println(password);
			System.out.println(r.getPassword());
			System.out.println(passwordEncoder.matches(password, r.getPassword()));
			System.out.println(password.equals(r.getPassword()));
			if (passwordEncoder.matches(password, r.getPassword()))
				return jwtTokenUtil.generateToken(r);
		}
		throw new CustomException("Invalid credentials: "+cred);
	}

}
