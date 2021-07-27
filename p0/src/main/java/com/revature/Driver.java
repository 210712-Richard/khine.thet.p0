package com.revature;

import java.io.IOException;

import com.revature.controllers.UserController;
import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
//	public static void main(String[] args) {
//		//starts the Javalin framework
//		//Javalin app = Javalin.create().start(8080);
//		UserController uc = new UserController();
//		
		public static void main(String[] args) throws IOException {
			Menu m = new Menu();
			m.start();
		//
	}
}
