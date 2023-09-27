package com.example.rider.controller;

import com.example.rider.model.dto.UpdatePasswordDTO;
import com.example.rider.model.entity.Rider;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@CrossOrigin("*")
public class RiderController {

	@Autowired
	private RiderService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/update-details")
	private ResponseEntity<?> update(@RequestBody Rider rider) {
		try {
			return ResponseEntity.ok(service.updateRiderDetails(rider));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/update-password")
	private ResponseEntity<?> updatePswd(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
		try {
			service.updateRiderPassword(updatePasswordDTO);
			return ResponseEntity.ok("Password updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

//	@GetMapping("/get-rider-details")
//	private ResponseEntity<?> getRiderDetails(@RequestBody String token) {
//		try {
//			return ResponseEntity.ok(service.authRiderDetails(token));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//	}
}
