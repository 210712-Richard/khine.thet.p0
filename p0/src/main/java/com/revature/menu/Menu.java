package com.revature.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.revature.service.*;
import com.revature.beans.*;
import com.revature.util.*;

public class Menu {
	private UserService us = new UserService();
	private Scanner scan = SingletonScanner.getScanner().getScan();
	private static User user = null;
	private User loggedUser = null;
	
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
					System.out.println("User does not exist.");		
				} else {
					loggedUser = user;
					System.out.println("Hello " + user.getUsername());
						 //go to either customer menu or banker menu
					switch(loggedUser.getTypes()) {
					case ADOPTER:
						adopter();
					case ADOPTEE:
						adoptee();
						break;
					case ADMIN:
						admin();
						break;
					}					
				} break;
			case 2:
				//Register account
				System.out.println("Enter your username.");
				while(true) {
					String username1 = scan.nextLine();
					User user = us.login(username1);
					if(user != null) {
						System.out.println("Username already exists. Please try another one.");			
					} else {
						System.out.println("Enter your email.");
						String email = scan.nextLine();
						while(!isValidEmail(email)) {
							System.out.println("Invalid input. Please try again.");
							email = scan.nextLine();					
						}
							System.out.println("Enter your birthday. (YYYY/MM/DD)");
//							String birthday = scan.nextLine();
//							System.out.println("Account successfully created.");
							List<Integer> bday = Stream.of(scan.nextLine().split("/"))
									.map((str) -> Integer.parseInt(str)).collect(Collectors.toList());
							
							LocalDate birth = LocalDate.of(bday.get(0), bday.get(1), bday.get(2));
							if(!us.checkBirthday(birth)) {
								System.out.println("You must be 18 years or older to adopt a pet.");
								continue main;
							}
							System.out.println("Account successfully created.");
					}
				}
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
	
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPattern.matcher(email);
		return matcher.find();
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
		System.out.println("Select:");
		System.out.println("\t1. Adoption application");
		System.out.println("\t2. Adoptee application");
		
		// TODO Auto-generated method stub
		
	}

	private void adopter() {
		System.out.println("Here are the available fur babies.");
		
	}

	private void adoptee() {
		System.out.println("Please fill out the form.");
		
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