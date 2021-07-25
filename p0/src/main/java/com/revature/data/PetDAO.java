package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.beans.Pet;
import com.revature.beans.User;

public class PetDAO {
	private static String filename = "pets.dat";
	private static List<Pet> pet;
	
	static {
		DataSerializer<Pet> ds = new DataSerializer<Pet>();
		pet = ds.readObjectsFromFile(filename);
		
		if(pet == null) {
			pet = new ArrayList<Pet>();
			pet.add(new Pet("Daisy", "Beagle", "brown", "6 months", "medium", "female"));
			pet.add(new Pet("Buddy", "Bulldog", "white", "2 years", "big", "male"));
			pet.add(new Pet("Jack", "French Bulldog", "white", "3 months", "medium", "male"));
			pet.add(new Pet("Lola", "Terrier", "black", "7 months", "small", "female"));
			pet.add(new Pet("Boba", "Corgi", "brown", "2 months", "small", "female"));
			pet.add(new Pet("Tucker", "Australian Shepherd", "black", "2 years", "medium", "male"));
			pet.add(new Pet("Rocky", "Pit Bull", "black", "8 months", "medium", "male"));
			pet.add(new Pet("Bell", "Chihuahua", "brown", "4 years", "small", "female"));
		}
		ds.writeObjectsToFile(pet, filename);		
	}
	
	public List<Pet> getUsers(){
		return pet;
	}
	
	public void writeToFile() {
		new DataSerializer<Pet>().writeObjectsToFile(pet, filename);
	}
}
