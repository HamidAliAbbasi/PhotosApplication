package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Users;

/**
 * Deserializes the data
 * @author Omar Atieh
 * @author Hamid Ali Abbasi
 */
public class deserialize {
	/**
	 * method returns deserialize an arrayList from userAccounts
	 * @param null
	 * @return Arraylist
	 * 
	 */

	public static ArrayList<Users> deserialize() {
		ArrayList<Users> storedUsers = null;
		try {
			FileInputStream fileIn = new FileInputStream("userAccounts.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			storedUsers = (ArrayList<Users>) in.readObject();
			in.close();
			fileIn.close();
			return storedUsers;

	}
	catch (ClassNotFoundException ex) {
		System.out.println("no class");
	}
	catch (IOException ex) {
		System.out.println("cant read file");
		
		
}
		return storedUsers;
} }