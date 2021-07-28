package com.revature;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.UserController;
import com.revature.menu.Menu;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		ObjectMapper jackson = JavalinJackson.defaultObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
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
		//As an adopter, I can see the available list of pets.
		app.get("/users/:username/getPet", uc::getPet);
		//As an adopter, I can apply for adoption.
		app.put("/users/:username/apply", uc::apply);
		//As an admin, I can add more pets for adoption.
		app.post("users/:username/addPet", uc::addPet);
		//As a user, I can change my address.
//		app.post("users/:username/changeAddress", uc::changeAddress);
//		As an admin, I can see the account informations.
//		app.get("users/:username/getAddress", uc::getAddress);
		
		
		
		
		
		
		
		
		
//		public static void main(String[] args) throws IOException {
//			Menu m = new Menu();
//			m.start();
		
	}
}
