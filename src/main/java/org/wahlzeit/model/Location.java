/*
 * Location.java
 * 
 * Version 1.0
 *
 * 2017/10/30
 * 
 * Copyright (c) 2017 by Kai-Malte B�hling
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

// Location Class
public class Location{
	
	/*
	 * Class Variables and Constants
	 */
	public CartesianCoordinate	cCoords;
	public SphericCoordinate 	sCoords;
	
	/**
	 * @methodtype Constructor
	 */
	public Location(double x, double y, double z) {
		cCoords = CartesianCoordinate.getCartesianCoordinate(x, y, z);
		
		SphericCoordinate tempCoord = null;
		double radius = Math.sqrt(x * x + y * y + z * z);
		if (radius == 0.0) {
			tempCoord = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
		}

		double longitude = Math.atan2(y, x);
		double latitude = Math.acos(z / radius);
		tempCoord = SphericCoordinate.getSphericCoordinate(longitude, latitude, radius);
		
		if(tempCoord != null) {
			sCoords = tempCoord;
		}
			
	}
	
}