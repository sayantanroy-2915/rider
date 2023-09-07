package com.example.rider.service;

import com.example.rider.exception.CustomException;
import com.example.rider.model.LoginReqDTO;
import com.example.rider.model.Rider;
import com.example.rider.model.RiderDetails;
import com.example.rider.model.UpdateDTO;
import com.example.rider.repository.RiderRepository;
import com.example.rider.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;

	public Rider addRider (Rider rider) {
		return repo.save(rider);
	}

	public String authRider(LoginReqDTO loginReqDTO) throws Exception {
		Optional<Rider> opr = repo.findByContact(loginReqDTO.getCred());
		if (opr.isPresent()) {
			Rider r = opr.get();
			if (passwordEncoder.matches(loginReqDTO.getPassword(), r.getPassword()))
				return jwtUtil.generateToken(r.getId());
			throw new CustomException("Invalid credentials: " + loginReqDTO.getCred());
		}
		throw new CustomException("Rider not found");
	}

	public RiderDetails authRiderDetails(String token) throws Exception {
		if (!jwtUtil.isTokenExpired(token)) {
			Long riderId = jwtUtil.getRiderId(token);
			Optional<Rider> opr = repo.findById(riderId);
			if (opr.isPresent())
				return new RiderDetails(opr.get());
			throw new CustomException("Rider not found");
		}
		throw new CustomException("Token expired");
	}

	public RiderDetails updateRiderDetails(UpdateDTO updateDTO) throws CustomException {
		Rider newRider = updateDTO.getRider();
		Rider oldRider = repo.findById(newRider.getId()).get();
		System.out.println(oldRider+"\n\t"+updateDTO.getOldPassword()+"\n\t"+oldRider.getPassword().substring(8));
		System.out.println(newRider+"\n\t"+newRider.getPassword());
		if (passwordEncoder.matches(updateDTO.getOldPassword(), oldRider.getPassword().substring(8))) {
			newRider.setPassword("{bcrypt}"+passwordEncoder.encode(newRider.getPassword()));
			return new RiderDetails(repo.save(newRider));
		}
		throw new CustomException("Old password is incorrect");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Rider> opr = repo.findByContact(username);
		System.out.println(opr);
		if (opr.isPresent())
			return opr.get();
		throw new UsernameNotFoundException("Rider not found");
	}
}
