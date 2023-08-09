package com.example.rider.controller;

import com.example.rider.model.Rider;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RiderController {

	@Autowired
	private RiderService service;

	@GetMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/registration")
	private ResponseEntity<?> register(@RequestBody Rider rider) {
		try {
			Rider r = service.addRider(rider);
			return ResponseEntity.ok("Rider named " + r.getName()+ " added with ID " + r.getId());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody Map<String, String> payload) { // payload: (cred, password)
		try {
			String token = service.authRider(payload.get("cred"), payload.get("password"));
			String cookieHeaderValue = "RiderAppJWT="+token+"; HttpOnly; Max-Age=86400; Path=/";
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookieHeaderValue).body("Login Successful");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
