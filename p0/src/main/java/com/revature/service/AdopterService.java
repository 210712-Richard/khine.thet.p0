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

	public static User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}

//	public static List<Pet> getList(String string) {
//		List<Pet> p = pd.getList();
//		return p;
//		
//	}
	
	public static void viewStatus() {
		//
	}
//
//	public List<Pet> getList(String string) {
//		List<Pet> p = pd.getList();
//		return p;
//	}

	public Pet display(String string) throws IOException {
		Pet p = pd.display();
		return p;
	}
}