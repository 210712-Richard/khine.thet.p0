package com.revature.menu;

import java.util.Scanner;
import com.revature.service.*;
import com.revature.beans.*;
import com.revature.util.*;
import com.revature.data.*;

public class Menu {
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		
		main: while(true) {
			switch(startMenu()) {
			// LOGIN
			case 1:
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				// get the user
				User u = us.login(username);
				// if user name is wrong
				if(u == null) {
					System.out.println("Please try again.");
				} else {
					loggedUser = u;
					System.out.println("Hello, " + u.getUsername());
					// go to either customer menu or banker menu
					switch(loggedUser.getAccountType()) {
					case CUSTOMER:
						customer();
						break;
					case ADMIN:
						admin();
						break;
					
					}
				}
				break;
			case 2:
				// register
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
	
	private void customer() {
		customer: while(true) {
			switch(customerMenu()) {
			case 1: 
				adoptor();
				break;
			
			case 2:
				adoptee();
				break;
			}
		}
	}
		
	private int customerMenu() {
		//Customer Menu	
		System.out.println("What would you like to do? Select from the menu.");
		System.out.println("\t1. I'm looking for fur babies to adopt!");
		System.out.println("\t2. I want to put up the fur babies for adpotion.");
		return select();
	}
	
	private void admin() {
		// TODO Auto-generated method stub
		
	}

	private void adoptor() {
		// TODO Auto-generated method stub
		
	}

	private void adoptee() {
		// TODO Auto-generated method stub
		
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