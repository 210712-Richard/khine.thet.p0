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
			pet.add(new Pet("Daisy", "Beagle", "brown", "6 months", "medium", "female"));
			pet.add(new Pet("Buddy", "Bulldog", "white", "2 years", "big", "male"));
			pet.add(new Pet("Jack", "French Bulldog", "white", "3 months", "medium", "male"));
			pet.add(new Pet("Lola", "Terrier", "black", "7 months", "small", "female"));
			pet.add(new Pet("Boba", "Corgi", "brown", "2 months", "small", "female"));
			pet.add(new Pet("Tucker", "Australian Shepherd", "black", "2 years", "medium", "male"));
			pet.add(new Pet("Rocky", "Pit Bull", "black", "8 months", "medium", "male"));
			pet.add(new Pet("Bell", "Chihuahua", "brown", "4 years", "small", "female"));
			
			ds.writeObjectsToFile(pet, filename);
		}		
	}
	
	public void addPet(Pet p) {
		pet.add(p);
	}
	
	public List<Pet> getList(String string) {
		System.out.println(pet);
		return pet;
	}
	
	public Pet getPet() {
		
		return pet.stream()
				.filter((p)-> p.getName().equals(filename))
				.findFirst()
				.orElse(null);
	}
	
	public Pet display() throws IOException {
		try {
			Scanner sc = new Scanner(new FileReader("pet.dat"));
			String s = "";
			while((s = sc.nextLine()) != null) {
				String[] data = s.split(",");
				int size = pet.size();
				for(int i = 0; i < size; i++) {
					System.out.println(data);
				}
				System.out.println();
			}
		} catch(Exception e) {
		System.out.println();
	}
		return null;
	}
	
	
	public void writeToFile() {
		new DataSerializer<Pet>().writeObjectsToFile(pet, filename);
	}

//	public Pet addPet(String name, String breed, String color, String age, String size, String sex) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public Pet addPet() {
		System.out.println(pet);
		return (Pet) pet;
	}


}
