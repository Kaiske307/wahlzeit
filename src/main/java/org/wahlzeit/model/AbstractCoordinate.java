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

import java.util.HashSet;

import org.wahlzeit.utils.DesignPattern;


@DesignPattern(
	patternName = "Template",
	patternParticipants = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
)
public abstract class AbstractCoordinate implements Coordinate{

	public static final double MAX_VARIANCE  = 0.0000001;
	public static final double DEFAULT_VALUE = 0.0;

	/**
	 * HashSet for shared Coordinates
	 */
	protected static HashSet<Coordinate> coordinates;

	/**
	 * Hash generation
	 */
	public abstract int getHash();
	
	@DesignPattern(
		patternName= "Converter",
		patternParticipants= {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
		)
	/**
	 * @methodtype conversion
	 */
	public abstract CartesianCoordinate asCartesianCoordinate();

	@DesignPattern(
		patternName= "Converter",
		patternParticipants= {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
		)
	/**
	 * @methodtype conversion
	 */
	public abstract SphericCoordinate asSphericCoordinate();

	/**
	 * @methodtype query-method
	 */
	public double getDistance(Coordinate coordinate) {
		assertNotNull(coordinate, AbstractCoordinate.class.getName(), "getDistance()");
		return 0.0;
	}

	/**
	 * @methodtype query-method
	 */
	public double getCartesianDistance(Coordinate coordinate) {
		return getDistance(coordinate);
	}

	/**
	 * @methodtype query-method
	 */
	public double getSphericDistance(Coordinate coordinate) {
		return getDistance(coordinate);
	}

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

	/**
	 * @methodtyp assertion
	 */
	void assertNotNull(Object object, String className, String function) {
		if(object == null) {
			throw new IllegalArgumentException("Object cannot be null in class" + className + "\tfunction:" + function);
		}
	}

}
