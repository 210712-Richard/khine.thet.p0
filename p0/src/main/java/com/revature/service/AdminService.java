package com.revature.service;

import com.revature.beans.Pet;
import com.revature.beans.User;

public interface AdminService {

	User login(String name);

	Pet addPet(String name, String breed, String color, String age, String size, String sex);

}