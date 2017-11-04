/*
 * Coordinate.java
 * 
 * ADAP-CW03
 *
 * 30.10.2017
 */

package org.wahlzeit.model;

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
	 * @methodtype get X Distance
	 */
	public double getXDistance(Coordinate distCoord) {
		double xDist = 0.0;
		
		xDist = getX() - distCoord.getX();
		
		if(xDist <= MAX_VARIANCE){
			xDist = 0.0;
		}
		
		return xDist;
	}
	
	/**
	 * @methodtype get Y Distance
	 */
	public double getYDistance(Coordinate distCoord) {
		double yDist = 0.0;
		
		yDist = getY() - distCoord.getY();
		
		if(yDist <= MAX_VARIANCE){
			yDist = 0.0;
		}
		
		return yDist;
	}
	
	/**
	 * @methodtype get Z Distance
	 */
	public double getZDistance(Coordinate distCoord) {
		double zDist = 0.0;
		
		zDist = getZ() - distCoord.getZ();
		
		if(zDist <= MAX_VARIANCE){
			zDist = 0.0;
		}
		return zDist;
	}
	
	/**
	 * @methodtype get Distance
	 */
	public double getDistance(Coordinate coord) {
		double distance = 0.0;
		
		double xDist = getXDistance(coord);
		double yDist = getYDistance(coord);
		double zDist = getZDistance(coord);
		
		distance = Math.abs(Math.pow(xDist, 2) + Math.pow(yDist, 2) + Math.pow(zDist, 2));
		
		return distance;
	}
	
	/**
	 * @methodtype check if Equal
	 */
	public boolean isEqual(Coordinate coord) {
		
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
		return this.isEqual((Coordinate) obj);
	}
}