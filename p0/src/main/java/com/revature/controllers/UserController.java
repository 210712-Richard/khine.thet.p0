package com.revature.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.AdoptionApplication;
import com.revature.beans.Pet;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.service.AdminService;
import com.revature.service.AdopterService;
import com.revature.service.UserService;

import io.javalin.http.Context;

public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	private AdminService as = new AdminService();
	private AdopterService adopters = new AdopterService();
	
	
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());

		User  u = ctx.bodyAsClass(User.class);
		System.out.println(u);
		u = us.login(u.getUsername());
		log.debug(u);
		ctx.json(u);
		
		if(u != null) {
			//saves the user object as logged user in the session
			ctx.sessionAttribute("loggedUser", u);
			ctx.json(u);
			return;
		}
		
		ctx.status(401);
		log.trace("Login not successful");
		
	}
	
	public void logout(Context ctx) {
		log.trace("Logout method called.");
		
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void register(Context ctx) {
		log.trace("Register method called.");
		
		User  u = ctx.bodyAsClass(User.class);
		
		if(us.checkAvailability(u.getUsername())) {
			User newUser = us.register(u.getUsername(), u.getEmail(), u.getBirthday(), u.getAddress());
			ctx.status(201);
			ctx.json(newUser);
			log.trace("Registeration complete.");
		} else {
			ctx.status(409);
			ctx.html("Name already taken.");
			log.trace("Username not available");
		}
	}
	
	public void addPet(Context ctx) {
		log.trace("addPet method was called");
		
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			log.trace("Not an authorized account");
			return;
		}
		// Check if we're an admin
		if(!loggedUser.getTypes().equals(UserType.ADMIN)) {
			ctx.status(403);
			log.trace("Not an authorized account");
			return;
		}
		
		Pet p = ctx.bodyAsClass(Pet.class);
		Pet newPet = as.addPet(p.getName(), p.getBreed(), p.getColor(), p.getAge(), p.getSize(), p.getSex());
		ctx.status(201);
		ctx.json(newPet);
		log.trace("Successfully added to the list.");
	}
	
	public void getPet(Context ctx) {
		log.trace("getPet method called");
		
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}		
		
		List<Pet> p = adopters.getPet();
		if(p == null) {
			ctx.status(403);
		} else {
			ctx.json(p);
			log.trace("List of pets shown.");
		}
	}
	
	public void apply(Context ctx) {
		log.trace("Apply method called");
		log.debug(ctx.body());
		
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		AdoptionApplication application = ctx.bodyAsClass(AdoptionApplication.class);
		
		ctx.json(application);
		AdoptionApplication newApplication = AdopterService.apply();
		ctx.status(201);
		ctx.json(newApplication);
		log.trace("Application submitted.");
		
	}
	
//	public void changeAddress(Context ctx) {
//		log.trace("changeAddress method called");
//		
//		String username = ctx.pathParam("username");
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		
//		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
//			ctx.status(403);
//			return;
//		}
//		
//		User u = ctx.bodyAsClass(User.class);
//		u = us.changeAddress(loggedUser, loggedUser.getAddress());
//		ctx.json(u);
//		log.trace("Address changed successfully");
//	}
//	
//	public void getAddress(Context ctx) {
//	log.trace("getAddress method called");
//	
//	String username = ctx.pathParam("username");
//	User loggedUser = ctx.sessionAttribute("loggedUser");
//	
//	if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
//		ctx.status(403);
//		return;
//	}
//	
//	if(!loggedUser.getTypes().equals(UserType.ADMIN)) {
//		ctx.status(403);
//		log.trace("Not an authorized account");
//		return;
//	}
//	
//}
//	
//	public void getStatus(Context  ctx) {
//		String username = ctx.pathParam("username");
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		
//		if(loggedUser == null || loggedUser.getUsername().equals(username)) {
//			ctx.status(403);
//			return;
//		}
//		
//	}
}
