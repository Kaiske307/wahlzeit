/*
 * Location.java
 * 
 * Version 1.0
 *
 * 2017/10/30
 * 
 * Copyright (c) 2017 by Kai-Malte Böhling
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
	public CartesianCoordinate Coords;
	
	/**
	 * @methodtype Constructor
	 */
	public Location(double x, double y, double z) {
		Coords = new CartesianCoordinate(x, y, z);
	}
	
}