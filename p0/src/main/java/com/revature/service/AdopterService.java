package com.revature.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.revature.data.PetDAO;
import com.revature.data.UserDAO;
import com.revature.beans.*;

public class AdopterService {
	public static PetDAO pd = new PetDAO();
	public static UserDAO ud = new UserDAO();
	public static AdoptionApplication application = new AdoptionApplication();

	public static User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}

	
	public static void apply() {
		User u = new User();
		if(u.getUsername() != application.getName()) {
			System.out.println("");
		}
	}
	
	public static Pet getAll() {
		Pet p = (Pet) pd.getPet();
		return p;
	}
	
	public static void viewStatus() {
		//
	}

}