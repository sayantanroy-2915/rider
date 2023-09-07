package com.example.rider.controller;

import com.example.rider.model.*;
import com.example.rider.service.UserService;
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
	private UserService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/update-all")
	private ResponseEntity<?> update(@RequestBody UpdateDTO updateDTO) {
		try {
			return ResponseEntity.ok(service.updateRiderDetails(updateDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
