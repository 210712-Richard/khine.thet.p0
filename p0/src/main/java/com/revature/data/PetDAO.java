package com.revature.data;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
			pet.add(new Pet(pet.size(), "Daisy", "Beagle", "brown", "6 months", "medium", "female"));
			pet.add(new Pet(pet.size(), "Buddy", "Bulldog", "white", "2 years", "big", "male"));
			pet.add(new Pet(pet.size(), "Jack", "French Bulldog", "white", "3 months", "medium", "male"));
			pet.add(new Pet(pet.size(), "Lola", "Terrier", "black", "7 months", "small", "female"));
			pet.add(new Pet(pet.size(), "Boba", "Corgi", "brown", "2 months", "small", "female"));
			pet.add(new Pet(pet.size(), "Tucker", "Australian Shepherd", "black", "2 years", "medium", "male"));
			pet.add(new Pet(pet.size(), "Rocky", "Pit Bull", "black", "8 months", "medium", "male"));
			pet.add(new Pet(pet.size(), "Bell", "Chihuahua", "brown", "4 years", "small", "female"));
			
			ds.writeObjectsToFile(pet, filename);
		}		
	}
	
	public void addPet(Pet p) {
		p.setId(pet.size());
		pet.add(p);
	}
	
	public Pet getPet(String username) {
		return pet.stream()
				.filter((pet) -> pet.getName().equals(username))
				.findFirst()
				.orElse(null);
	}
	
	public List<Pet> getPet() {
		return pet;
	}
	
	public void writeToFile() {
		new DataSerializer<Pet>().writeObjectsToFile(pet, filename);
	}
}
