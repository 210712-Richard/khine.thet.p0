package com.revature;

import java.io.IOException;

import com.revature.controllers.UserController;
import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		//starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		UserController uc = new UserController();
		
		app.get("/", (ctx)->ctx.html("Hello World"));
		
		// object::method <- Reference to a method as a function we can pass to a method
		
		// As a user, I can log in.
		app.post("/users", uc::login);
		// As a user, I can register for a player account.
		app.put("/users/:username", uc::register);
		// As a user, I can log out.
		app.delete("/users", uc::logout);
		
		//As an admin, I can add more pets for adoption.
		app.post("users/:username/addpet", uc::addPet);
		
		app.get("users/:username/birthday", uc::getBirthday);
		
		
		
		
		
		
//		public static void main(String[] args) throws IOException {
//			Menu m = new Menu();
//			m.start();
		
	}
}
