package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.service.UserServiceImp;

import io.javalin.http.Context;

public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServiceImp us = new UserServiceImp();
	
	public static void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
	}
}
