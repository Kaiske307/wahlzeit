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

import java.io.IOException;

public class Coordinate{

	/*
	 * Class Variables and Constants
	 */
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	public static final double DEFAULT_Z = 0.0;
	public static final double MAX_VARIANCE = 0.0000001;
	private double x;
	private double y;
	private double z;
	
	/**
	 * @methodtype Constructor
	 */
	public Coordinate() {
		this.x = DEFAULT_X;
		this.y = DEFAULT_Y;
		this.z = DEFAULT_Z;
	}
	
	/**
	 * @methodtype Constructor
	 */
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @methodtype set x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @methodtype set Y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	* @methodtype set Z
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * @methodtype get X
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @methodtype get Y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @methodtype get Z
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * @throws IOException 
	 * @methodtype get X Distance
	 */
	public double getXDistance(Coordinate distCoord) throws IOException {
		
		if(distCoord == null) {
			throw new IOException("getXDistance() distCoord is null");
		}
				
		double xDist = 0.0;
		
		xDist = Math.abs(getX() - distCoord.getX());
		
		if(xDist <= MAX_VARIANCE){
			xDist = 0.0;
		}
		
		return xDist;
	}
	
	/**
	 * @throws IOException 
	 * @methodtype get Y Distance
	 */
	public double getYDistance(Coordinate distCoord) throws IOException {
		
		if(distCoord == null) {
			throw new IOException("getYDistance() distCoord is null");
		}
		
		
		double yDist = 0.0;
		
		yDist = Math.abs(getY() - distCoord.getY());
		
		if(yDist <= MAX_VARIANCE){
			yDist = 0.0;
		}
		
		return yDist;
	}
	
	/**
	 * @throws IOException 
	 * @methodtype get Z Distance
	 */
	public double getZDistance(Coordinate distCoord) throws IOException {
		
		if(distCoord == null) {
			throw new IOException("getZDistance() - distCoord is null");
		}
		
		double zDist = 0.0;
		
		zDist = Math.abs(getZ() - distCoord.getZ());
		
		if(zDist <= MAX_VARIANCE){
			zDist = 0.0;
		}
		return zDist;
	}
	
	/**
	 * @throws IOException 
	 * @methodtype get Distance
	 */
	public double getDistance(Coordinate coord) throws IOException {
		
		if(coord == null) {
			throw new IOException("getDistance() coord is null");
		}
		
		double distance = 0.0;
		
		double xDist = getXDistance(coord);
		double yDist = getYDistance(coord);
		double zDist = getZDistance(coord);
		
		distance = Math.abs(Math.pow(xDist, 2) + Math.pow(yDist, 2) + Math.pow(zDist, 2));
		
		return distance;
	}
	
	/**
	 * @throws IOException 
	 * @methodtype check if Equal
	 */
	public boolean isEqual(Coordinate coord) throws IOException {
		
		if(coord == null) {
			throw new IOException("isEqual() - coord is null");
		}
			
		double distance = getDistance(coord);
		
		if(distance == 0.0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		try {
			return this.isEqual((Coordinate) obj);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}