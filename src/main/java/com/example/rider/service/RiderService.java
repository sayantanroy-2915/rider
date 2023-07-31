package com.example.rider.service;

import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderService {

	@Autowired
	private RiderRepository repo;

	public Rider addRider (Rider rider) throws Exception {
		repo.save(rider);
		return rider;
	}

}
