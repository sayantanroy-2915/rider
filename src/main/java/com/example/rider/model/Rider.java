package com.example.rider.model;

import javax.persistence.*;

@Entity
@Table(name = "riders")
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;


	@Column(unique = true)
	private String phone;

	@Column(unique = true)
	private String email;

	@Column
	private String password;

	public Rider() {}

	public Rider(String name, String phone, String email, String password) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
