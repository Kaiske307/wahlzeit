package org.wahlzeit.model;



// Location Class
public class Location{
	
	/*
	 * Class Variables and Constants
	 */
	private Coordinates Coords;
	private String		Name;
	
	
	/**
	 * @methodtype Constructor
	 */
	public Location(double x, double y, String name) {
		Coords = new Coordinates(x, y);
		Name = name;
	}
	
	/**
	 * @methodtype Set Location Name
	 */
	public void setLocationName(String name) {
		Name = name;
	}
	
	/**
	 * @methodtype get Location Name
	 */
	public String getLocationName() {
		return Name;
	}
	
	/**
	 * @methodtype get Coordinates
	 */
	public Coordinates getCoordinates() {
		return Coords;
	}
	
	/**
	 * @methodtype set Coordinates
	 */
	public void setCoordinates(Coordinates newCoords) {
		if(!Coords.isEqual(newCoords)){
			Coords.setX(newCoords.getX());
			Coords.setY(newCoords.getY());
		}
	}
}