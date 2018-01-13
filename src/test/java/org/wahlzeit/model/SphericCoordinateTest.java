/**
 * 16.12.2017
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {

	private static final double MAX_DELTA = 1E-10;
	private static final double EARTH_RADIUS = 6371000; // Median Earth Radius needed for Spherical Distance Test
	
	private SphericCoordinate sphericCoord1;
	private SphericCoordinate sphericCoord2;
	private SphericCoordinate sphericCoord3;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sphericCoord1 = new SphericCoordinate();
		sphericCoord2 = new SphericCoordinate(Math.PI / 4, Math.PI, 7.6);
		sphericCoord3 = new SphericCoordinate(0.33, -1.2, 4.4);
	}
	
	/**
	 * Test Methods for Constructors
	 */
	@Test
	public void testConstructors() {
		assertNotNull(sphericCoord1);
		assertNotNull(sphericCoord2);
		assertNotNull(sphericCoord3);

		//check properties after creation
		assertEquals(0.0, sphericCoord1.getLongitude(), MAX_DELTA);
		assertEquals(0.0, sphericCoord1.getLatitude(),  MAX_DELTA);
		assertEquals(0.0, sphericCoord1.getRadius(),    MAX_DELTA);

		assertEquals(Math.PI / 4, sphericCoord2.getLongitude(), MAX_DELTA);
		assertEquals(Math.PI, 	  sphericCoord2.getLatitude(),  MAX_DELTA);
		assertEquals(7.6, 		  sphericCoord2.getRadius(),    MAX_DELTA);

		assertEquals(0.33, sphericCoord3.getLongitude(), MAX_DELTA);
		assertEquals(-1.2, sphericCoord3.getLatitude(),  MAX_DELTA);
		assertEquals(0.33, sphericCoord3.getRadius(),    MAX_DELTA);

	}

	/**
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#asCartesianCoordinate()}.
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#asSphericCoordinate()}.
	 */
	@Test
	public void testCoordinateTransformation() {
		assertEquals(sphericCoord2.asSphericCoordinate(), sphericCoord2);

		assertEquals(sphericCoord1.asCartesianCoordinate().asSphericCoordinate(), sphericCoord1);
		assertEquals(sphericCoord2.asCartesianCoordinate().asSphericCoordinate(), sphericCoord2);
		assertEquals(sphericCoord3.asCartesianCoordinate().asSphericCoordinate(), sphericCoord3);
	}

	/**
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#getDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testGetDistance() {
		//check distance to itself
		assertEquals(0.0, sphericCoord1.getDistance(sphericCoord1), 0.0);
		assertEquals(0.0, sphericCoord2.getDistance(sphericCoord2), 0.0);
		assertEquals(0.0, sphericCoord3.getDistance(sphericCoord3), 0.0);

		//check same distance in both directions
		assertEquals(sphericCoord1.getDistance(sphericCoord2), sphericCoord2.getDistance(sphericCoord1), MAX_DELTA);
		assertEquals(sphericCoord2.getDistance(sphericCoord3), sphericCoord3.getDistance(sphericCoord2), MAX_DELTA);

		//check correct distance
		assertEquals(sphericCoord3.getRadius(), sphericCoord3.getDistance(new SphericCoordinate()), MAX_DELTA);

		//check invalid distance
		assertEquals(Double.POSITIVE_INFINITY, sphericCoord1.getDistance(null), 0.0);
	}

	/**
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#getCartesianDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testGetCartesianDistance() {
		double distS1_S2 = sphericCoord1.asCartesianCoordinate().getCartesianDistance(sphericCoord2); 
		double distS2_S1 = sphericCoord2.asCartesianCoordinate().getCartesianDistance(sphericCoord1);
		
		assertEquals(distS1_S2, sphericCoord1.getCartesianDistance(sphericCoord2), MAX_DELTA);
		assertEquals(distS2_S1, sphericCoord2.getCartesianDistance(sphericCoord1), MAX_DELTA);
	}

	/**
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#getSphericDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testSphericDistance() {
		// Eg Hometown to Erlangen
		// https://www.kompf.de/trekka/distance.php?lat1=52.5164&lon1=13.3777&lat2=38.692668&lon2=-9.177944
		SphericCoordinate homeTown = new SphericCoordinate(0.17091, 0.92705, EARTH_RADIUS);
		SphericCoordinate erlangen = new SphericCoordinate(0.19219, 0.8655,  EARTH_RADIUS);
		
		double distance = homeTown.getDistance(erlangen);
		assertEquals(distance, 401.6, 1.0);
		
		CartesianCoordinate homeTownCart = homeTown.asCartesianCoordinate();
		CartesianCoordinate erlangenCart = erlangen.asCartesianCoordinate();

		assertEquals(distance, erlangenCart.getSphericDistance(homeTownCart), 2.0);
		assertEquals(distance, erlangenCart.getSphericDistance(homeTown), 2.0);
		assertEquals(distance, homeTown.getSphericDistance(erlangenCart), 2.0);

		assertEquals(Double.POSITIVE_INFINITY, homeTown.getSphericDistance(null), 0.0);
		assertEquals(Double.POSITIVE_INFINITY, homeTownCart.getSphericDistance(null), 0.0);
	}

}
