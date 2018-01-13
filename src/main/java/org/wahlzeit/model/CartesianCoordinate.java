/**
 * Coordinate.java
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

import java.util.HashSet;

import org.wahlzeit.utils.DesignPattern;

import com.google.common.base.Objects;

@DesignPattern(
		patternName = "Value Object",
		patternParticipants = {"CartesianCoordinate"}
		)
public final class CartesianCoordinate extends AbstractCoordinate {

	static {
		coordinates = new HashSet<Coordinate>();
	}
	
	/*
	 * Class Variables and Constants
	 */
	private final double	x;
	private final double	y;
	private final double	z;

	/**
	 * @methodtype Constructor
	 */
	public CartesianCoordinate() {
		this.x = DEFAULT_VALUE;
		this.y = DEFAULT_VALUE;
		this.z = DEFAULT_VALUE;
	}

	/**
	 * @methodtype Constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	/**
	 * @methodtype Constructor
	 */
	public CartesianCoordinate(Coordinate coordinate) {
		assertNotNull(coordinate, CartesianCoordinate.class.getName(), "CartesianCoordinate()");

		this.x = coordinate.asCartesianCoordinate().getX();
		this.y = coordinate.asCartesianCoordinate().getY();
		this.z = coordinate.asCartesianCoordinate().getZ();
		assertClassInvariants();
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#getHash()
	 */
	@Override
	public int getHash() {
		return Objects.hashCode(x, y, z);
	}
	
	/**
	 * @methodtype getter
	 * Original Author StrategicallyInefficient
	 */
	public CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		
		CartesianCoordinate tempCoord = new CartesianCoordinate(x, y, z);
		
		if(coordinates.contains(tempCoord)) {
			for (Coordinate coord : coordinates) {
				if (coord.equals(tempCoord)) {
					return (CartesianCoordinate) coord;
				}
			}
		}
		
		coordinates.add(tempCoord);
		return tempCoord;
	}

	/**
	 * @methodtype getter
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype getter
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype getter
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype getter
	 */
	public double getXDistance(Coordinate distCoord) {
		assertNotNull(distCoord, CartesianCoordinate.class.getName(), "getXDistance()");

		double xDist = 0.0;

		xDist = Math.abs(getX() - distCoord.asCartesianCoordinate().getX());

		if (xDist <= MAX_VARIANCE) {
			xDist = 0.0;
		}

		return xDist;
	}

	/**
	 * @methodtype getter
	 */
	public double getYDistance(Coordinate distCoord) {
		assertNotNull(distCoord, CartesianCoordinate.class.getName(), "getYDistance()");

		double yDist = 0.0;

		yDist = Math.abs(getY() - distCoord.asCartesianCoordinate().getY());

		if (yDist <= MAX_VARIANCE) {
			yDist = 0.0;
		}

		return yDist;
	}

	/**
	 * @methodtype getter
	 */
	public double getZDistance(Coordinate distCoord) {
		assertNotNull(distCoord, CartesianCoordinate.class.getName(), "getZDistance()");

		double zDist = 0.0;

		zDist = Math.abs(getZ() - distCoord.asCartesianCoordinate().getZ());

		if (zDist <= MAX_VARIANCE) {
			zDist = 0.0;
		}
		return zDist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wahlzeit.model.Coordinate#getCartesianDistance(org.wahlzeit.model.
	 * Coordinate)
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertNotNull(coordinate, CartesianCoordinate.class.getName(), "getCartesianDistance()");

		double distance = 0.0;

		double xDist = this.asCartesianCoordinate().getXDistance(coordinate);
		double yDist = this.asCartesianCoordinate().getYDistance(coordinate);
		double zDist = this.asCartesianCoordinate().getZDistance(coordinate);

		distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2) + Math.pow(zDist, 2));

		return distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wahlzeit.model.Coordinate#getSphericDistance(org.wahlzeit.model.
	 * Coordinate)
	 */
	@Override
	public double getSphericDistance(Coordinate coordinate) {
		return this.asSphericCoordinate().getSphericDistance(coordinate);
	}

	/**
	 * @methodtype getter
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean equals(Object obj) {
		assertNotNull(obj, CartesianCoordinate.class.getName(), "equals()");

		if (!(obj instanceof CartesianCoordinate)) {
			return false;
		}
		return this.isEqual((CartesianCoordinate) obj);
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(x * x + y * y + z * z);
		if (radius == 0.0) {
			return new SphericCoordinate(0.0, 0.0, 0.0);
		}

		double longitude = Math.atan2(y, x);
		double latitude = Math.acos(z / radius);

		return new SphericCoordinate(longitude, latitude, radius);
	}

	private void assertClassInvariants() {
		if(Double.isNaN(x)) {
			throw new IllegalArgumentException("X-Coordinate is not a Number");
		}
		if(Double.isNaN(y)) {
			throw new IllegalArgumentException("Y-Coordinate is not a Number");	
		}
		if(Double.isNaN(z)) {
			throw new IllegalArgumentException("Z-Coordinate is not a Number");
		}
		
		if (x > Double.MAX_VALUE && x < Double.MIN_VALUE) {
			throw new IllegalArgumentException("X-Coordinate out of Range");
		}
		
		if (y > Double.MAX_VALUE && y < Double.MIN_VALUE) {
			throw new IllegalArgumentException("X-Coordinate out of Range");
		}
		if (z > Double.MAX_VALUE && z < Double.MIN_VALUE) {
			throw new IllegalArgumentException("X-Coordinate out of Range");
		}
	}

}
