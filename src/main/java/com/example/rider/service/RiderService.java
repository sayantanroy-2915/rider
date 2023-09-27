package com.example.rider.service;

import com.example.rider.exception.CustomException;
import com.example.rider.model.entity.Rider;
import com.example.rider.model.dto.RiderDetails;
import com.example.rider.model.dto.UpdatePasswordDTO;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RiderService implements UserDetailsService {

	@Autowired
	private RiderRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private TokenService tokenService;


//	public String authRider(LoginReqDTO loginReqDTO) throws Exception {
//		Optional<Rider> opr = repo.findByContact(loginReqDTO.getCred());
//		if (opr.isPresent()) {
//			Rider r = opr.get();
//			if (passwordEncoder.matches(loginReqDTO.getPassword(), r.getPassword()))
//				return tokenService.generateToken(r.getId());
//			throw new CustomException("Invalid credentials: " + loginReqDTO.getCred());
//		}
//		throw new CustomException("Rider not found");
//	}

//	public RiderDetails authRiderDetails(String token) throws Exception {
//		if (!jwtUtil.isTokenExpired(token)) {
//			Long riderId = jwtUtil.getRiderId(token);
//			Optional<Rider> opr = repo.findById(riderId);
//			if (opr.isPresent())
//				return new RiderDetails(opr.get());
//			throw new CustomException("Rider not found");
//		}
//		throw new CustomException("Token expired");
//	}

	public RiderDetails updateRiderDetails(Rider newRider) throws CustomException {
		Rider oldRider = repo.findById(newRider.getId()).orElse(null);
		System.out.println("Old data: "+oldRider);
		System.out.println("Updated Data: "+newRider);
		if (passwordEncoder.matches(newRider.getPassword(), Objects.requireNonNull(oldRider).getPassword().substring(8))) {
			newRider.setPassword(oldRider.getPassword());
			return new RiderDetails(repo.save(newRider));
		}
		throw new CustomException("Password is incorrect");
	}

	public void updateRiderPassword(UpdatePasswordDTO updatePasswordDTO) throws CustomException {
		System.out.println(updatePasswordDTO);
		Rider rider = repo.findById(updatePasswordDTO.getId()).get();
		System.out.println(rider);
		if (passwordEncoder.matches(updatePasswordDTO.getOldPassword(), rider.getPassword().substring(8))) {
			rider.setPassword("{bcrypt}"+passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
			repo.save(rider);
		}
		else
			throw new CustomException("Password is incorrect");
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
