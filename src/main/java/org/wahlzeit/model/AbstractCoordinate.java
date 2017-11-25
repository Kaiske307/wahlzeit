/**
 * 25.11.2017
 * 
 * org.wahlzeit.model
 * 
 * Copyright (c) 2017 Kai-Malte Böhling
 *
 * This file is part of the Wahlzeit rating application.
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

public abstract class AbstractCoordinate implements Coordinate{

	public static final double MAX_VARIANCE  = 0.0000001;
	public static final double DEFAULT_VALUE = 0.0;
	
	/**
	 *	@methodtype constructor
	 */
	public AbstractCoordinate() {
		// TODO Auto-generated constructor stub
	}
	
	/** 
	 * @methodtype conversion
	 */
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	/**
	 * @methodtype conversion
	 */
	public abstract SphericCoordinate asSphericCoordinate();
	  
	/**
	 * @methodtype query-method
	 */
	public abstract double getDistance(Coordinate coordinate);
	 
	/**
	 * @methodtype query-method
	 */
	public abstract double getCartesianDistance(Coordinate coordinate);
	 
	/**
	 * @methodtype query-method
	 */
	public abstract double getSphericDistance(Coordinate coordinate);
	
	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean isEqual(Coordinate coordinate) {
		if (coordinate != null) {
			return isDoubleEqual(this.getDistance(coordinate), 0.0);
		}
		return false;
	}
	
	/**
	 * @methodtype boolean query
	 */
	private static boolean isDoubleEqual(double a, double b) {
		return a == b ? true : Math.abs(a - b) <= MAX_VARIANCE;
	}
}
