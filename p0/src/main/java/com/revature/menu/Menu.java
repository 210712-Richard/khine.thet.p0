package com.revature.menu;

import java.util.Scanner;
import com.revature.service.*;
import com.revature.beans.*;
import com.revature.util.*;


public class Menu {
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		
		main: while(true) {
			switch(startMenu()) {
			case 1:
				// Login
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				// Call the user service to find the user we want.
				User u = us.login(username);
				if(u == null) {
					System.out.println("Invalid username. Please try again.");
				} else {
					loggedUser = u;
					System.out.println("Welcome back: "+u.getUsername());
					// call either the Customer menu or the Admin menu, depending on user)
					
					switch(loggedUser.getAccountType()) {
					case CUSTOMER:
						System.out.println("What would you like to do? Select from the menu.");
						//Continue here//
						adpotee();
						adoptor();
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
	
	private void admin() {
		// TODO Auto-generated method stub
		
	}

	private void adoptor() {
		// TODO Auto-generated method stub
		
	}

	private void adpotee() {
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