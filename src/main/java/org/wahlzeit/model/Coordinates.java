package org.wahlzeit.model;

public class Coordinates{

	/*
	 * Class Variables and Constants
	 */
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	private double X;
	private double Y;
	
	/**
	 * @methodtype Constructor
	 */
	public Coordinates() {
		setX(DEFAULT_X);
		setY(DEFAULT_Y);
	}
	
	/**
	 * @methodtype Constructor
	 */
	public Coordinates(double x, double y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * @methodtype set x
	 */
	public void setX(double x) {
		X = x;
	}
	
	/**
	 * @methodtype set Y
	 */
	public void setY(double y) {
		Y = y;
	}
	
	/**
	 * @methodtype get X
	 */
	public double getX() {
		return X;
	}
	
	/**
	 * @methodtype get Y
	 */
	public double getY() {
		return Y;
	}
	
	/**
	 * @methodtype get X Distance
	 */
	public double getXDistance(Coordinates distCoord) {
		double xDist = 0.0;
		
		xDist = getX() - distCoord.getX();
		
		return xDist;
	}
	
	/**
	 * @methodtype get Y Distance
	 */
	public double getYDistance(Coordinates distCoord) {
		double yDist = 0.0;
		
		yDist = getY() - distCoord.getY();
		
		return yDist;
	}
	
	/**
	 * @methodtype get Distance
	 */
	public Coordinates getDistance(Coordinates coord) {
		double xDist = getXDistance(coord);
		double yDist = getYDistance(coord);
		
		return new Coordinates(xDist, yDist);
	}
	
	/**
	 * @methodtype check if Equal
	 */
	public boolean isEqual(Coordinates coord) {
		Coordinates distance = getDistance(coord);
		
		if((distance.getX() == 0.0) &&
		   (distance.getY() == 0.0)) {
			return true;
		}
		
		return false;
	}
}