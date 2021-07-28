package com.revature.service;

import java.time.LocalDate;
import java.time.Period;

import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserService {

	public UserDAO ud = new UserDAO();

	public User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}

	public User register(String name, String email, LocalDate birthday, String address) {
		User u = new User();
		u.setUsername(name);
		u.setEmail(email);
		u.setBirthday(birthday);
		u.setAddress(address);
		ud.addUser(u);
		return u;
	}

	public boolean checkAvailability(String newName) {
		return ud.getUsers().stream().noneMatch((u) -> u.getUsername().equals(newName));
	}

	public boolean checkBirthday(LocalDate birth) {
		LocalDate now = LocalDate.now();
		LocalDate ageReq = now.minus(Period.of(18, 0, 0));
		// log.debug(sixteenYearsAgo);
		ageReq = ageReq.plus(Period.of(0, 0, 1));
		// log.debug(sixteenYearsAgo);
		return birth.isBefore(ageReq);
	}

	public User changeAddress(User u, String address) {
		u.setEmail(address);
		ud.writeToFile();
		return u;
	}
	
	public boolean checkStatus() {
		// TODO Auto-generated method stub
		return false;
	}
}
