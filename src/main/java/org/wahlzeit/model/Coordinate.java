/**
 * * 19.11.2017
 * 
 * org.wahlzeit.model
 * 
 * Copyright (c) 2017 Kai-Malte B�hling
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


public interface Coordinate {

	/** 
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate();
	
	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate();
	  
	/**
	 * @methodtype boolean-query.
	 */
	public boolean isEqual(Coordinate coordinate);
	  
	/**
	 * @methodtype query-method
	 */
	public double getDistance(Coordinate coordinate);
	 
	/**
	 * @methodtype query-method
	 */
	public double getCartesianDistance(Coordinate coordinate);
	 
	/**
	 * @methodtype query-method
	 */
	public double getSphericDistance(Coordinate coordinate);
	
}
