package com.revature.menu;

import java.time.LocalDate;
import java.util.Scanner;
import com.revature.service.*;
import com.revature.beans.*;
import com.revature.util.*;
import com.revature.data.*;

public class Menu {
	private UserService us = new UserService();
	private Scanner scan = SingletonScanner.getScanner().getScan();
	private static User user = null;
	
	public void start() {
		
		main: while(true) {
			
			switch(startMenu()) {
			// LOGIN
			case 1:
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				// get the user
				user = us.login(username);
				// if user name is wrong
				if(user == null) {
					System.out.println("Please try again.");
				} else if(user.getTypes() == UserType.ADOPTER){
					System.out.println("Hello, " + user.getUsername());
					adopter();
				} else if(user.getTypes() == UserType.ADOPTEE) {
					System.out.println("Hello, " + user.getUsername());
					adoptee();
				} else if (user.getTypes() == UserType.ADMIN) {
					System.out.println("Welcome back, " + user.getUsername());
					admin();
				}
//					switch(customerMenu()) {
						// go to either customer menu or banker menu
//					switch(loggedUser.getTypes()) {
//					case ADOPTER:
//						adopter();
//					case ADOPTEE:
//						adoptee();
//						break;
//					case ADMIN:
//						admin();
//						break;
//					
//					}
				break;
			case 2:
				register();
				break;
			case 3:
				// quit
				System.out.println("Goodbye!");
				break main;
			default:
				// invalid selection
				System.out.println("Not a valid selection, please try again.");
			}
		}
	}

	private int startMenu() {
		//Start Menu
		System.out.println("Welcome to Pawfect Pals!\n What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		return select();
	}
	
	private void admin() {
		// TODO Auto-generated method stub
		
	}

	private void adopter() {
		System.out.println("Here are the available fur babies.");
		
	}

	private void adoptee() {
		System.out.println("Please fill out the form.");
		
	}
	
	private void register() {
		String username;
		String email;
		String birthday;
		String address = null;
		LocalDate localDate;
		System.out.println("Enter your username.");
		while(true) {
			username = scan.nextLine();
			User user = us.login(username);
			if(user != null) {
				System.out.println("There is an error with your registration. Please try again.");			
			} else {
				System.out.println("Enter your email.");
				email = scan.nextLine();
				System.out.println("Enter your birthday. (MM/DD/YYYY");
				birthday = scan.nextLine();
				localDate = LocalDate.parse(birthday, null);
				break;
			}
			
		}
		us.register(username, email, localDate, address);
	}

	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}
}