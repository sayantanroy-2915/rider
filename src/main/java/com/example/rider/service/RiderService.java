package com.example.rider.service;

import com.example.rider.exception.CustomException;
import com.example.rider.model.AuthReq;
import com.example.rider.model.AuthUpdate;
import com.example.rider.model.Rider;
import com.example.rider.model.RiderDetails;
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
		return repo.save(rider);
	}

	public String authRider(AuthReq authReq) throws Exception {
		Optional<Rider> opr = repo.findByContact(authReq.getCred());
		if (opr.isPresent()) {
			Rider r = opr.get();
			if (passwordEncoder.matches(authReq.getPassword(), r.getPassword()))
				return jwtUtil.generateToken(r.getId());
			throw new CustomException("Invalid credentials: " + authReq.getCred());
		}
		throw new CustomException("Rider not found");
	}

	public RiderDetails authRiderDetails(String jwt) throws Exception {
		if (!jwtUtil.isTokenExpired(jwt)) {
			Long riderId = jwtUtil.getRiderId(jwt);
			Optional<Rider> opr = repo.findById(riderId);
			if (opr.isPresent())
				return new RiderDetails(opr.get());
			throw new CustomException("Rider not found");
		}
		throw new CustomException("Token expired");
	}

/*	private boolean update(AuthUpdate authUpdate) {
		switch (authUpdate.getField()) {
			case "password":

		}
	}*/

}
