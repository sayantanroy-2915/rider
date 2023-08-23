package com.example.rider.controller;

import com.example.rider.model.*;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rider")
@CrossOrigin("*")
public class RiderController {

	@Autowired
	private RiderService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/signup-api")	// From API call, encodes password
	private ResponseEntity<?> signupApi(@RequestBody RiderRegReq riderRegReq) {
		try {
			Rider rider = new Rider(riderRegReq);
			rider.setPassword(passwordEncoder.encode(rider.getPassword()));
			rider = service.addRider(rider);
			return ResponseEntity.status(HttpStatus.CREATED).body("Rider named " + rider.getName()+ " added with ID " + rider.getId());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/signup-web")	// From Frontend, doesn't encode password
	private ResponseEntity<?> signupWeb(@RequestBody RiderRegReq riderRegReq) {
		try {
			Rider r = service.addRider(new Rider(riderRegReq));
			return ResponseEntity.status(HttpStatus.CREATED).body("Rider named " + r.getName()+ " added with ID " + r.getId());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody AuthReq authReq) {
		try {
			String token = service.authRider(authReq);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	@GetMapping("/get-rider-details")
	private ResponseEntity<?> getRiderDetails(@RequestBody String token) {
		try {
			return ResponseEntity.ok(service.authRiderDetails(token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
