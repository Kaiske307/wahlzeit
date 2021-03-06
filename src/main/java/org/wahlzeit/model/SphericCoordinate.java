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

import java.util.HashSet;
import org.wahlzeit.utils.DesignPattern;
import com.google.common.base.Objects;

@DesignPattern(
	patternName = "Value Object",
	patternParticipants = {"SphericCoordinate"}
	)
public final class SphericCoordinate extends AbstractCoordinate{

	static {
		coordinates = new HashSet<Coordinate>();
	}
	
	/**
	 * Class Variables and Constants
	 */
	private final double MAX_LONGITUDE = Math.PI;
	private final double MIN_LONGITUDE = 0.0;
	private final double MAX_LATITUDE  = Math.PI;
	private final double MIN_LATITUDE  = -Math.PI;
	private final double longitude;	// 0 to pi
	private final double latitude;	// -pi to pi
	private final double radius;

	/**
	 * @methodtype Constructor
	 */
	private SphericCoordinate(double longitude, double latitude, double radius) {
		// DbC
		assertLongitude(longitude);
		assertLatitude(latitude);
		assertRadius(radius);

		this.longitude 	= longitude;
		this.latitude  	= latitude;
		this.radius 	= radius;

		assertClassInvariants();
	}

	/**
	 * @methodtype Constructor
	 */
	private SphericCoordinate(Coordinate coordinate) {
		assertNotNull(coordinate, SphericCoordinate.class.getName(), "SphericCoordiante(Coordiante)");

		longitude = coordinate.asSphericCoordinate().getLongitude();
		latitude  = coordinate.asSphericCoordinate().getLatitude();
		radius	  = coordinate.asSphericCoordinate().getRadius();

		assertClassInvariants();
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#getHash()
	 */
	@Override
	public int getHash() {
		return Objects.hashCode(longitude, latitude, radius);
	}
	
	/**
	 * @methodtype getter
	 * Original Author StrategicallyInefficient
	 */
	public static SphericCoordinate getSphericCoordinate(double longitude, double latitude, double radius) {
		
		SphericCoordinate tempCoord = new SphericCoordinate(longitude, latitude, radius);
		
		if(coordinates.contains(tempCoord)) {
			for (Coordinate coord : coordinates) {
				if (coord.equals(tempCoord)) {
					return (SphericCoordinate) coord;
				}
			}
		}
		
		coordinates.add(tempCoord);
		return tempCoord;
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
	public double getSphericDistance(Coordinate coordinate) {
		assertNotNull(coordinate, SphericCoordinate.class.getName(), "getSpericDistance()");

		SphericCoordinate other = ((AbstractCoordinate) coordinate).asSphericCoordinate();
		SphericCoordinate own = this.asSphericCoordinate();
		double deltaAngle = Math.acos(Math.sin(own.getLatitude()) * Math.sin(other.getLatitude()) +
							Math.cos(own.getLatitude()) * Math.cos(other.getLatitude()) *
							Math.cos(Math.abs(own.getLongitude() - other.getLongitude())));

		double distance = deltaAngle * Math.max(own.getRadius(), other.getRadius());

		assert distance >= 0.0 && distance <= Double.MAX_VALUE;
		return distance;
	}


	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean equals(Object obj) {
		assertNotNull(obj, SphericCoordinate.class.getName(), "equals()");

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
		assertClassInvariants();

		double x = radius * Math.cos(longitude) * Math.sin(latitude);
		double y = radius * Math.sin(longitude) * Math.sin(latitude);
		double z = radius * Math.cos(longitude);

		return CartesianCoordinate.getCartesianCoordinate(x, y, z);
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

	/**
	 * @methodtype assertion
	 */
	private void assertClassInvariants() {
		assertLongitude(longitude);
		assertLatitude(latitude);
		assertRadius(radius);
	}

	/**
	* @methodtype assertion
	*/
	private void assertLongitude(double longitude) {
		if(	longitude > MAX_LONGITUDE &&
			longitude < MIN_LONGITUDE ){
				throw new IllegalArgumentException("Longitude out of Range");
			}
	}

	/**
	* @methodtype assertion
	*/
	private void assertLatitude(double latitude ) {
		if( latitude > MAX_LATITUDE &&
			latitude < MIN_LATITUDE ){
				throw new IllegalArgumentException("Latitude out of Range");
			}
	}

	/**
	* @methodtype assertion
	*/
	private void assertRadius(double radius) {
		if (radius < 0.0){
			throw new IllegalArgumentException("Radius cannot be negative");
		}
	}
}
