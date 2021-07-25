package com.revature;

import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		//starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		UserController uc = new UserController();
		
		app.get("/", (ctx) -> ctx.html("Hello World"));
		
		app.post("/users", uc::login);
		app.get("users/:username/birthday", uc::getBirthday);
	}
}
