package com.revature.service;
import java.time.LocalDate;
import com.revature.beans.User;
import com.revature.data.UserDAO;

	public class UserService {
	
		private UserDAO ud = new UserDAO();
	
		public User login(String name) {
			User u = ud.getUser(name);
			return u;
	}
	
	
	
}