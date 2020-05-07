package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Photos> photos;
    /**
     * 
   	 * Constructor to add an album for a user 
   	 * provides an arraylist of photos
   	 * @param a Album to add
   	 * @return nothing
   	 */
    public Album(String name){
        this.name = name;
        photos = new ArrayList<Photos>();
        
    }
    /**
   	 * Method to get photos
   	 * @param null
   	 * @return arrayList of photos
   	 */
    public ArrayList<Photos> getAlbumPhotos() {
        return this.photos;

    }
    /**
   	 * Method to get photos amount in album
   	 * @param null
   	 * @return int of arraylist size
   	 */
    public int getPhotoQuantity() {
        return this.photos.size();
    }
    /**
   	 * Method to get album name
   	 * @param null
   	 * @return string of name
   	 */
    public String getName(){
        return this.name;
    }
    /**
   	 * Method to set album name
   	 * @param String of new name
   	 * @return void
   	 */
    public void setName(String name){
        this.name = name;
    }
    /**
   	 * Method to get add a photo
   	 * @param Photos pic 
   	 * @return void
   	 */
    public void addPhoto(Photos pic){
        photos.add(pic);

    }
    /**
   	 * Method to get remove a photo
   	 * @param Photos Pic
   	 * @return void
   	 */
    public void removePhoto(Photos pic){
        if(photos.contains(pic)) photos.remove(pic);

    }
    /**
   	 * Method to get String of album
   	 * @param null
   	 * @return string of name
   	 */
    public String toString() {
		return this.name;
	}
}