package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.service.UserServiceImp;

import io.javalin.http.Context;

public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServiceImp us = new UserServiceImp();
	
	
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
	
	public void getBirthday(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		ctx.json(loggedUser.getBirthday());
	}
}
