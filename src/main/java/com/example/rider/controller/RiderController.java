package com.example.rider.controller;

import com.example.rider.model.AuthReq;
import com.example.rider.model.AuthRes;
import com.example.rider.model.Rider;
import com.example.rider.model.RiderRegReq;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RiderController {

	@Autowired
	private RiderService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/signupapi")	// From API call, encodes password
	private ResponseEntity<?> register(@RequestBody RiderRegReq riderRegReq) {
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

	@PostMapping("/signupweb")	// From Frontend, doesn't encode password
	private ResponseEntity<?> signup(@RequestBody RiderRegReq riderRegReq) {
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
			return ResponseEntity.ok(new AuthRes(service.authRider(authReq)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
