package model;

import java.io.Serializable;

public class Tags implements Serializable{

	private static final long serialVersionUID = 1L;
    private String type;
    private String value;


    /**
	 * Constructor
	 * @param type the name of the tag
	 * @param value the value of the tag
	 */
    public Tags(String type, String value){
        
        this.type = type;
        this.value = value;
    }

    /**
	 * Method to get tag type
	 * @return name of tag
	 */
    public String getTagType(){

        return this.type;
    }

    /**
	 *Method to get tag value
	 * @return value of tag
	 */
    public String getTagValue(){

        return this.value;
    }
    
    /**
	 * Method to Compare tags
	 * @param other tag to compare with
	 * @return true if the tags are equal, false otherwise
	 */
    public boolean equals(Tags other) {
        
        return type.equals(other.type) && value.equals(other.value);
    }
    
    /**
	 * Method to get string representation of the tag
	 * @return a string representation of the tag
	 */
    public String toString() {
        
        return type + " : " + value;
	}


}