package com.example.rider.model;


/**
 * Rider details except password
 * To send data to the Rider
 */
public class RiderDetails {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String status;

    public RiderDetails(Rider rider) {
		this.id = rider.getId();
        this.name = rider.getName();
        this.phone = rider.getPhone();
        this.email = rider.getEmail();
        this.status = rider.getStatus();
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
}
