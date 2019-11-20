package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Wesley Cheung
 * @author Dennis Yu
 */
public class Album implements Serializable {

	private static final long serialVersionUID = 1891567810783724951L;
	private String name;
	private ArrayList<Photo> photos;
	private Calendar minDate;
	private Calendar maxDate;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Album(String name) {
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	/**
	 * Gets the name of this album
	 * @return the name of this album
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of this album
	 * @param name the new name of this album
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 */
	public Calendar getMinDate() {
		return this.minDate;
	}
	
	/**
	 * 
	 */
	public Calendar getMaxDate() {
		return this.maxDate;
	}
	
	/**
	 * 
	 */
	public void updateDateRange() {
		if(!this.photos.isEmpty()) {
			Collections.sort(this.photos);
		}
		this.minDate = this.photos.get(0).getDate();
		this.maxDate = this.photos.get(photos.size()-1).getDate();
	}
	
	/**
	 * Gets the photos in this album
	 * @return an arraylist of photos
	 */
	public ArrayList<Photo> getPhotos() {
		return this.photos;
	}
	
	/**
	 * Returns the number of photos in this album
	 * @return the number of photos in this album
	 */
	public int getPhotoCount() {
		return this.photos.size();
	}
	
	/**
	 * Compares this album to another
	 * @param other the album to be compared
	 * @return true if the albums are equal, false otherwise
	 */
	public boolean equals(Album other) {
		return name.equals(other.name);
	}
	
	/**
	 * Returns a string representation of this album
	 */
	public String toString() {
		//Date minRange = this.minDate.getInstance().getTime();
		//Date maxRange = this.maxDate.getInstance().getTime();
		String minRange = "Null";
		String maxRange = "Null";
		
		if(!photos.isEmpty()) {
			
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		minRange = formatter.format(this.minDate.getTime());  
		maxRange = formatter.format(this.maxDate.getTime());
		
		}
		
		String result = "Name: " + name + "\nPhoto Count: " + photos.size() + "\nDate Range: " + minRange + " - " + maxRange; 
		
		return result;
	}
}
