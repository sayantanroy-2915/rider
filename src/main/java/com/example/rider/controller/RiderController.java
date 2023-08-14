package com.example.rider.controller;

import com.example.rider.model.AuthReq;
import com.example.rider.model.AuthRes;
import com.example.rider.model.Rider;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

	@RequestMapping("/")
	private String home() {
		return "Welcome Rider";
	}

	@PostMapping("/registration")
	private ResponseEntity<?> register(@RequestBody Rider rider) {
		try {
			Rider r = service.addRider(rider);
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
