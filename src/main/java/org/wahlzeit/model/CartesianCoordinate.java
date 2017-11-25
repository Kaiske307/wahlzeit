/*
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


public class CartesianCoordinate extends AbstractCoordinate{

	/*
	 * Class Variables and Constants
	 */
	private double x;
	private double y;
	private double z;
	
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
	}
	
	/**
	 * @methodtype Constructor
	 */
	public CartesianCoordinate(Coordinate coordinate) {
		this.x = coordinate.asCartesianCoordinate().getX();
		this.y = coordinate.asCartesianCoordinate().getY();
		this.z = coordinate.asCartesianCoordinate().getZ();
	}
	
	/**
	 * @methodtype setter
	 */
	public void setCartesianCoordinates(CartesianCoordinate coordinate) {
		x = coordinate.getX();
		y = coordinate.getY();
		z = coordinate.getZ();
	}
	
	/**
	 * @methodtype setter
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	* @methodtype setter
	 */
	public void setZ(double z) {
		this.z = z;
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
	public double getXDistance(Coordinate distCoord){
		
		if(distCoord == null) {
			return Double.POSITIVE_INFINITY;
		}
				
		double xDist = 0.0;
		
		xDist = Math.abs(getX() - distCoord.asCartesianCoordinate().getX());
		
		if(xDist <= MAX_VARIANCE){
			xDist = 0.0;
		}
		
		return xDist;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getYDistance(Coordinate distCoord){
		
		if(distCoord == null) {
			return Double.POSITIVE_INFINITY;
		}
		
		double yDist = 0.0;
		
		yDist = Math.abs(getY() - distCoord.asCartesianCoordinate().getY());
		
		if(yDist <= MAX_VARIANCE){
			yDist = 0.0;
		}
		
		return yDist;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getZDistance(Coordinate distCoord){
		
		if(distCoord == null) {
			return Double.POSITIVE_INFINITY;
		}
		
		double zDist = 0.0;
		
		zDist = Math.abs(getZ() - distCoord.asCartesianCoordinate().getZ());
		
		if(zDist <= MAX_VARIANCE){
			zDist = 0.0;
		}
		return zDist;
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.Coordinate#getCartesianDistance(org.wahlzeit.model.Coordinate)
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		if(coordinate == null) {
			return Double.POSITIVE_INFINITY;
		}
				
		double distance = 0.0;
		
		double xDist = this.asCartesianCoordinate().getXDistance(coordinate);
		double yDist = this.asCartesianCoordinate().getYDistance(coordinate);
		double zDist = this.asCartesianCoordinate().getZDistance(coordinate);
		
		distance = Math.abs(Math.pow(xDist, 2) + Math.pow(yDist, 2) + Math.pow(zDist, 2));
		
		return distance;
	}

	/* (non-Javadoc)
	 * @see org.wahlzeit.model.Coordinate#getSphericDistance(org.wahlzeit.model.Coordinate)
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
		if (obj == null) {
			return false;
		}
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

}