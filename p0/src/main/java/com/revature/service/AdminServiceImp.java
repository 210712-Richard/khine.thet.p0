package com.revature.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.revature.beans.Pet;
import com.revature.beans.User;
import com.revature.data.PetDAO;
import com.revature.data.UserDAO;

public class AdminServiceImp implements AdminService {
	public PetDAO pd = new PetDAO();
	public UserDAO ud = new UserDAO();
		
	@Override
	public User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}

	@Override
	public Pet addPet(String name, String breed, String color, String age, String size, String sex) {
		Pet p = pd.addPet(name, breed, color, age, size, sex);
		return p;
		
	}
	
	
}
