package com.revature.data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAO {
	private static List<User> users;
	private static String filename = "user.dat";
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		//id, name, email, birthday, address
		if(users == null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "Dana Lynn", "dnlynn@email.com", LocalDate.of(1990,  8, 6), "1234 Summer Street, Toledo OH 11223"));
			users.add(new User(users.size(), "Tony Chopper", "tonychopper@email.com", LocalDate.of(1957, 12, 24), "75 Ocean Avenue, Brooklyn NY, 12345"));
			users.add(new User(users.size(), "Mary Smith", "ms8816@gmail.com", LocalDate.of(1988, 6, 13), "980 Park Avenue, Bridgeport CT, 13227"));
			User u = new User(users.size(), "Lilia Sung", "lilias@gmail.com", LocalDate.of(1970, 7, 7), "1709 17th Street, Jacksonville FL, 87654");
			u.setType(UserType.ADMIN);
			users.add(u);
			
			ds.writeObjectsToFile(users, filename);
		}
	}
	
	public User getUser(String username) {
		
			for(User user : users) {
				if(user.getUsername().equals(username)) {
					return user;
				}
			}
		
			return null;
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeObjectsToFile(users, filename);
	}
	
	
	

}
