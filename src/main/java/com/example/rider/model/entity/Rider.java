package com.example.rider.model.entity;

import com.example.rider.model.dto.RegisterDTO;
import com.example.rider.model.RiderRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Entity class representing a rider
 */
@Entity
@Table(name = "riders")
public class Rider implements UserDetails {

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

	@Column
	private String city;

	public Rider() {
		super();
	}

	public Rider(String name, String phone, String email, String password, String city) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.city = city;
	}

	public Rider(RegisterDTO registerDTO) {
		super();
		this.name = registerDTO.getName();
		this.phone = registerDTO.getPhone();
		this.email = registerDTO.getEmail();
		this.password = registerDTO.getPassword();
		this.city = registerDTO.getCity();
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Rider{" +
				"id=" + id +
				", name='" + name + "'" +
				", phone='" + phone + "'" +
				((email != null && !email.isEmpty())?(", email='" + email + "'"):"") +
				", city='" + city + "'}";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<RiderRole> authority = new HashSet<>();
		authority.add(new RiderRole());
		return authority;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
