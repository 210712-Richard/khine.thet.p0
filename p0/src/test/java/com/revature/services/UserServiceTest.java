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
		u.setUsername("test"); 
		u.setEmail("test@test.test");
		u.setBirthday(LocalDate.of(1998, 1, 1));
		u.setAddress("test");
	}
	
	@BeforeEach
	public void setupTest() {
		service = new UserService();
		service.ud = Mockito.mock(UserDAO.class);
	}
	
	@Test
	public void testLogin() {
		String username = "user";
		service.login(username);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		assertEquals(username, "Assert that the login user is the same as username.");
		
	}
	
	@Test
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
		
		
		service.register(u.getUsername(), u.getEmail(), u.getBirthday(), u.getAddress());
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

		Mockito.verify(service.ud).addUser(captor.capture());
		
		User u = captor.getValue();
		assertEquals(UserType.ADOPTER, u.getTypes(), "Asserting starting type is ADOPTER");
		assertEquals(u.getUsername(), u.getUsername(), "Asserting username is correct");
		assertEquals(u.getEmail(), u.getEmail(), "Asserting email is correct");
		assertEquals(u.getBirthday(), u.getBirthday(), "Asserting birthday is correct");
		assertEquals(u.getAddress(), u.getAddress(), "Asserting address is correct");
	}

	
//	@Test
//	public void testCheckBirthday() {
//		//TODO 		
//	}
}
