package com.example.rider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiderApplication.class, args);
	}
}
