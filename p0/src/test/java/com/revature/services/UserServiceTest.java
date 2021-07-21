package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDAO;
import com.revature.service.UserService;

public class UserServiceTest {
	private static UserService service;
	private static User u;
	
	@BeforeAll
	public static void setupClass() {
		u = new User();
		u.setUsername("Test");
	}
	
	@BeforeEach
	public void setupTest() {
		service = new UserService();
		service.ud = Mockito.mock(UserDAO.class);
	}
	
	@Test //Why is it failure trace 
	public void testCheckAvailabilityInThrowsException() {
		assertThrows(NullPointerException.class, () -> {service.checkAvailability(null);});
	}
	
	@Test
	public void testCheckAvailabilityInReturnsTrue() {
		String u = null;
		assertTrue(service.checkAvailability(u));
	}
	
	@Test
	public void testRegister() {
		String username = "test";
		String email = "test@test.test";
		LocalDate date = LocalDate.of(2020, 2, 2);
		String address = "test";
		service.register(username, email, date, address);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

		Mockito.verify(service.ud).addUser(captor.capture());

		Mockito.verify(service.ud).writeToFile();

		User u = captor.getValue();
		assertEquals(username, u.getUsername(), "Asserting username is correct");
		assertEquals(email, u.getEmail(), "Asserting email is correct");
		assertEquals(date, u.getBirthday(), "Asserting birthday is correct");
		assertEquals(address, u.getAddress(), "Asserting address is correct");
	}
	
//	@Test
//	public void testCheckBirthday() {
//		//TODO 		
//	}
}
