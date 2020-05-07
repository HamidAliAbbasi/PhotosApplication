package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.*;
public class Users implements Serializable {
    
    
	private static final long serialVersionUID = 1L;
    private  String username;
    private ArrayList<Album> albums; 

    /**
	 * saves new instance of a unique user
	 * @return nothing
	 */
   
    public Users(String username){

        this.username = username;
 
    }

    /**
	 * Methods to get the username of this user
	 * @return username of this user
	 */
    public String getUserName(){

        return this.username;
    }

    /**
	 * Method to get Albums
	 * @return Albums of user
	 */
    public ArrayList<Album> getAlbums() {
        
        return albums;
	}
    
    
	/**
	 * Method to get string representation of user
	 * @return string representation of user
	 */
	public String toString() {
        
        return this.username;
	}
    
    
	/**
	 * Method to Check if there is another user with same name
	 * @param other the user to be compared to
	 * @return true if users are equal, false otherwise
	 */
	public boolean equals(Users other) {
        
        return this.username.equals(other.username);
    } 
    
    
    /**
	 * Method to add an album for a user
	 * @param a Album to add
	 * @return nothing
	 */
    public void addAlbums(Album a) {
		System.out.println("adding: "+a.getName()+" to: "+albums.toString());
		this.albums.add(a);
	}

	public void setAlbums(ArrayList<Album> t){
	this.albums =t;
		
		
	}
    /**
	 * Method to remove an album for a user
	 * @param a Album to remove
	 * @return nothing
	 */
    public void deleteAlbum(Album a) {
		this.albums.remove(a);
    }
    

    
}