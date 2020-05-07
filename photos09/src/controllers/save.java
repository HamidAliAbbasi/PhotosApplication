package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import model.Users;

/**
 * Class to save the user data
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */


public class save {
	public static final String path = "userAccounts.dat";
	/**
	 * method returns serialize an arrayList to userAccounts
	 * @param null
	 * @return null
	 * 
	 */
	public static void save(ArrayList<Users> users) {
	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("userAccounts.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(users);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
