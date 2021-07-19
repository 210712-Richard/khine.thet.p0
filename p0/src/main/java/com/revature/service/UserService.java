package com.revature.service;

import java.time.LocalDate;

import com.revature.beans.User;
import com.revature.data.UserDAO;

	public class UserService {
	
		private UserDAO ud = new UserDAO();
	
		public User login(String name) {
			User u = ud.getUser(name);
			ud.writeToFile();
			return u;
		}
	
		public User register(String name, String email, LocalDate birthday, String address) {
			User u = ud.createUser(name, email, birthday, address);
			return u;
		}
	
}