package com.example.rider.model.dto;

public class UpdatePasswordDTO {

	private Long id;
	private String oldPassword;
	private String newPassword;


	public Long getId() {
		return id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDTO{" +
				"id=" + id +
				", oldPassword='" + oldPassword + '\'' +
				", newPassword='" + newPassword + '\'' +
				'}';
	}
}
