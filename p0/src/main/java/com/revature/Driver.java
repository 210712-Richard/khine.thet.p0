package com.revature;

import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		//starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		
		
		app.get("/", (ctx) -> ctx.html("Hello World"));
	}
}
