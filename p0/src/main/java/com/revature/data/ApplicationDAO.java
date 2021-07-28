package com.revature.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.AdoptionApplication;

public class ApplicationDAO {
	private static List<AdoptionApplication> application;
	private static String filename = "adoptionApplication.dat";
	
	static {
		DataSerializer<AdoptionApplication> ds = new DataSerializer<AdoptionApplication>();
		application = ds.readObjectsFromFile(filename);
		
		//id, name, email, birthday, address
		if(application == null) {
			application = new ArrayList<AdoptionApplication>();
			application.add(new AdoptionApplication(application.size(), "Mary Smith", "980 Park Avenue, Bridgeport CT, 13227", LocalDate.of(1988, 06, 13)));
			application.add(new AdoptionApplication(application.size(), "Yaeda", "75 E 5th Street , Newark NJ, 09876", LocalDate.of(1957, 12, 24)));
			
			ds.writeObjectsToFile(application, filename);
		}
	}
}
