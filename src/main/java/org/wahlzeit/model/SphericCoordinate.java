/**
 * 19.11.2017
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

public class SphericCoordinate implements Coordinate {

	/*
	 * Class Variables and Constants
	 */
	public static final double DEFAULT_LONG = 0.0;
	public static final double DEFAULT_LAT	= 0.0;
	public static final double DEFAULT_RAD	= 0.0;
	public static final double MAX_VARIANCE = 0.0000001;
	private double longitude;	// 0 to pi
	private double latitude;	// -pi to pi
	private double radius;
	
	/**
	 * @methodtype Constructor
	 */
	public SphericCoordinate() {
		longitude 	= DEFAULT_LONG;
		latitude  	= DEFAULT_LAT;
		radius		= DEFAULT_RAD;
	}
	
	/**
	 * @methodtype Constructor
	 */
	public SphericCoordinate(double longitude, double latitude, double radius) {
		this.longitude 	= longitude;
		this.latitude  	= latitude;
		this.radius 	= radius;
	}
	
	/**
	 * @methodtype Constructor
	 */
	public SphericCoordinate(Coordinate coord) {
		longitude = coord.asSphericCoordinate().getLongitude();
		latitude  = coord.asSphericCoordinate().getLatitude();
		radius	  = coord.asSphericCoordinate().getRadius();
	}
	
	/**
	 * @methodtype setter
	 */
	public void setSphericCoordinate(Coordinate coord) {
		longitude = coord.asSphericCoordinate().getLongitude();
		latitude  = coord.asSphericCoordinate().getLatitude();
		radius	  = coord.asSphericCoordinate().getRadius();
	}
		
	/**
	 * @methodtype setter
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	* @methodtype setter
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getRadius() {
		return radius;
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.Coordinate#getDistance(org.wahlzeit.model.Coordinate)
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		return getSphericDistance(coordinate);
	}
		
	/**
	 * @methodtype getter
	 */
	@Override
	public double getSphericDistance(Coordinate coord) {
		if(coord == null) {
			return Double.POSITIVE_INFINITY;
		}
				
		SphericCoordinate other = coord.asSphericCoordinate();
		SphericCoordinate own = this.asSphericCoordinate();
		double deltaAngle = Math.acos(Math.sin(own.getLatitude()) * Math.sin(other.getLatitude()) +
							Math.cos(own.getLatitude()) * Math.cos(other.getLatitude()) *
							Math.cos(Math.abs(own.getLongitude() - other.getLongitude())));
		 	
		double distance = deltaAngle * Math.max(own.getRadius(), other.getRadius());
		
		return distance;
	}
	
	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate coord){
		if(coord == null) {
			return false;
		}
			
		double distance = getDistance(coord);
		
		if(distance <= MAX_VARIANCE) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SphericCoordinate)) {
			return false;
		}
		return this.isEqual((SphericCoordinate) obj);
	}

	 /**
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        double x = radius * Math.cos(longitude) * Math.sin(latitude);
        double y = radius * Math.sin(longitude) * Math.sin(latitude);
        double z = radius * Math.cos(longitude);

        return new CartesianCoordinate(x, y, z);
    }

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}


	/**
	 * @methodtype query
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

}
