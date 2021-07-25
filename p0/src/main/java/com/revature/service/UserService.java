package com.revature.service;

import java.time.LocalDate;

import com.revature.beans.User;

public interface UserService {

	User login(String name);

	User register(String name, String email, LocalDate birthday, String address);

	boolean checkAvailability(String newName);

	boolean checkBirthday(LocalDate birth);

}