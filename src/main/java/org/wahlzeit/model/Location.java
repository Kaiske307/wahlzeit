/*
 * Location.java
 * 
 * ADAP-CW03
 *
 * 30.10.2017
 */

package org.wahlzeit.model;



// Location Class
public class Location{
	
	/*
	 * Class Variables and Constants
	 */
	private Coordinate Coords;
	
	/**
	 * @methodtype Constructor
	 */
	public Location(double x, double y, double z) {
		Coords = new Coordinate(x, y, z);
	}
	
}