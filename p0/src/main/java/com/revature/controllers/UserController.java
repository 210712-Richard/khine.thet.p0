package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Pet;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.service.AdminService;
import com.revature.service.UserService;

import io.javalin.http.Context;

public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	private AdminService as = new AdminService();
	
	
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
		//Send 401 if login is not successful
		ctx.status(401);
		
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
		} else {
			ctx.status(409);
			ctx.html("Name already taken.");
		}
	}
	
	public void getBirthday(Context ctx) {
		log.trace("getBirthday method called");
		
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		ctx.json(loggedUser.getBirthday());
	}
	

	public void addPet(Context ctx) {
		log.trace("addPet method was called");
		
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		// Check if we're an admin
		if(!loggedUser.getTypes().equals(UserType.ADMIN)) {
			ctx.status(403);
			return;
		}
		
		Pet np = ctx.bodyAsClass(Pet.class);
		np.setName(np.getName());
		np.setBreed(np.getBreed());
		np.setColor(np.getColor());
		np.setAge(np.getColor());
		np.setSize(np.getSize());
		np.setSex(np.getSex());
		ctx.json(as.addPet(np.getName(), np.getBreed(), np.getColor(), np.getAge(), np.getSize(), np.getSex()));
	}
	
	public void getStatus(Context  ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		
		
	}
}
