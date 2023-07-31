package com.example.rider.controller;

import com.example.rider.model.Rider;
import com.example.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
			return ResponseEntity.ok(rider.getId());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
