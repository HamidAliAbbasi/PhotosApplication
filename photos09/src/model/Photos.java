package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;

import java.util.Date;
/**
 * Class to make a photo
 * @author omar atieh
 * @author Hamid Abbassi
 */
public class Photos implements Serializable{
	   
    private static final long serialVersionUID = 1L;
    private String name;
    private String caption;
    private File file;
	private Date date;
    private ArrayList<Tags> tags;
    /**
	 * Constructor to make a photo requires a file
	 * @param File file
	 * 
	 */
    public Photos(File file){
        
        this.file = file;
        this.caption = "";
		this.date = new Date(file.lastModified());
        this.tags = new ArrayList<Tags>();

    }
    
    /**
   	 * Constructor to make a photo requires a file
   	 * @param File file
   	 * @param String caption of photo
   	 * 
   	 */
 public Photos(File file, String caption){
        
        this.file = file;
        this.caption = caption;
		this.date = new Date(file.lastModified());
        this.tags = new ArrayList<Tags>();

    }
 /**
	 * method returns name of photo
	 * @return String of name
	 */
    public String getName() {
    
        return name;
    }
    /**
   	 * method returns name of caption
   	 * @param null
   	 * @return String of caption
   	 * 
   	 */
    public String getCaption() {
        
        return this.caption;
    }
    /**
   	 * method returns to set name of caption
   	 * @param String of new Caption
   	 * @return null
   	 * 
   	 */
    public void setCaption (String newCaption) {
        
        this.caption = newCaption;
    }
    /**
   	 * method remove caption
   	 * @param null
   	 * @return void
   	 * 
   	 */
    public void removeCaption () {
        
        this.caption = "";
    }
    /**
   	 * method returns ArrayList of Tags
   	 * @param null
   	 * @return ArrayList of Tags
   	 * 
   	 */
    public ArrayList<Tags> getTags(){
        
        return this.tags;
    }
    /**
   	 * method returns amount of tags
   	 * @param null
   	 * @return int of size
   	 * 
   	 */
    public int getTagsQuantity(){

        return this.tags.size();
    }
    
    /**
   	 * method returns date of photo
   	 * @param null
   	 * @return String of date format
   	 * 
   	 */
	public String getDateString(){
		return new SimpleDateFormat("MM/dd/yy").format(this.getDate());
	}
	  /**
   	 * method return date in string 
   	 * @param null
   	 * @return String of date
   	 * 
   	 */
    public String getDate(){

        return this.date+"";
    }
    
    /**
   	 * method returns file  used
   	 * @param null
   	 * @return String File of picture
   	 * 
   	 */
	public File getFile() {
		return this.file;
	}
	  /**
   	 * method compare the name of a photo to another
   	 * @param Photo of of what you want to compare
   	 * @return boolean if true or false
   	 * 
   	 */
    public boolean equals(Photos other) {
        
        return this.name.equals(other.name);
    } 
    /**
   	 * method returns path of a photo
   	 * @param null
   	 * @return String of path
   	 * 
   	 */
    public String getPath() {
        
        return this.file.getPath();
	}
    
}