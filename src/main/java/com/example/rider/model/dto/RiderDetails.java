package com.example.rider.model.dto;


import com.example.rider.model.entity.Rider;

/**
 * Rider details except password
 * To send data to the Rider
 */
public class RiderDetails {

	private final Long id;
	private final String name;
	private final String phone;
	private final String email;
	private final String city;

    public RiderDetails(Rider rider) {
		this.id = rider.getId();
        this.name = rider.getName();
        this.phone = rider.getPhone();
        this.email = rider.getEmail();
		this.city = rider.getCity();
    }

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}
}
