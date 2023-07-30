package com.example.rider.model;

import javax.persistence.*;

@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column
	String name;

	@Column(unique = true)
	String phone;

	@Column
	String email;

	@Column
	String password;


}
