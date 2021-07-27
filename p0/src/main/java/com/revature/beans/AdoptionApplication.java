package com.revature.beans;

import java.time.LocalDate;

public class AdoptionApplication {
	private String name;
	private String address;
	private LocalDate birthday;
	private User u;
	
	public AdoptionApplication() {
		super();
	}
	
	public AdoptionApplication(String name, String address, LocalDate birthday, User u) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.u = u;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
}
