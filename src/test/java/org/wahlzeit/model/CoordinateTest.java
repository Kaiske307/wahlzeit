/*
 * Coordinate Test
 * 
 * Version 1.1
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// Test Cases for Coordinate Class

public class CoordinateTest {
	private static final double MAX_DELTA = 1E-10;
	private static final double EARTH_RADIUS = 6371000; // Median Earth Radius needed for Spherical Distance Test

    private CartesianCoordinate cartCoord1;
    private CartesianCoordinate cartCoord2;
    private CartesianCoordinate cartCoord3;

    private SphericCoordinate sphericCoord1;
    private SphericCoordinate sphericCoord2;
    private SphericCoordinate sphericCoord3;
	
	@Before
	public void setUp() throws Exception {

       cartCoord1 = new CartesianCoordinate();
	   cartCoord2 = new CartesianCoordinate(10.6, 8.4, 7.6);
	   cartCoord3 = new CartesianCoordinate(8.3, 6.7, 4.4);

	   sphericCoord1 = new SphericCoordinate();
	   sphericCoord2 = new SphericCoordinate(Math.PI / 4, Math.PI, 7.6);
       sphericCoord3 = new SphericCoordinate(0.33, -1.2, 4.4);
	}
	

	public void testConstructors() {
        assertNotNull(cartCoord1);
        assertNotNull(cartCoord2);
        assertNotNull(cartCoord3);

        assertNotNull(sphericCoord1);
        assertNotNull(sphericCoord2);
        assertNotNull(sphericCoord3);

        //check properties after creation
        assertEquals(0.0, cartCoord1.getX(), MAX_DELTA);
        assertEquals(0.0, cartCoord1.getY(), MAX_DELTA);
        assertEquals(0.0, cartCoord1.getZ(), MAX_DELTA);

        assertEquals(10.6, cartCoord1.getX(), MAX_DELTA);
        assertEquals(8.4,  cartCoord1.getY(), MAX_DELTA);
        assertEquals(7.6,  cartCoord1.getZ(), MAX_DELTA);

        assertEquals(8.3, cartCoord3.getX(), MAX_DELTA);
        assertEquals(6.7, cartCoord3.getY(), MAX_DELTA);
        assertEquals(4.4, cartCoord3.getZ(), MAX_DELTA);


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
	
    @Test
    public void testCartesianEquals() {
    	// Null Check
        assertFalse(cartCoord1.equals(null));
        assertFalse(cartCoord1.isEqual(null));
        assertFalse(cartCoord1.equals(new Object()));

        // Getter Test
        assertFalse(cartCoord2.equals(cartCoord3));
        cartCoord3.setX(cartCoord2.getX());
        cartCoord3.setY(cartCoord2.getY());
        cartCoord3.setZ(cartCoord2.getZ());
        assertTrue(cartCoord2.isEqual(cartCoord3));
        assertTrue(cartCoord2.equals(cartCoord3));

        // With Different Parameter
        cartCoord3.setX(4.1);
        assertFalse(cartCoord2.isEqual(cartCoord3));
        assertFalse(cartCoord2.equals(cartCoord3));
        cartCoord3.setX(cartCoord2.getX());

        cartCoord3.setY(4.2);
        assertFalse(cartCoord2.equals(cartCoord3));
        cartCoord3.setX(cartCoord2.getY());

        cartCoord3.setZ(6.3);
        assertFalse(cartCoord2.equals(cartCoord3));
        cartCoord3.setZ(cartCoord2.getZ());
    }

    /**
     *
     */
    @Test
    public void testSphericEquals() {
        // Null Check
        assertFalse(sphericCoord1.equals(null));
        assertFalse(sphericCoord1.isEqual(null));
        assertFalse(sphericCoord1.equals(new Object()));

        // Getter Test
        assertFalse(sphericCoord2.equals(sphericCoord3));
        sphericCoord3.setRadius(sphericCoord2.getRadius());
        sphericCoord3.setLatitude(sphericCoord2.getLatitude());
        sphericCoord3.setLongitude(sphericCoord2.getLongitude());
        assertTrue(sphericCoord2.isEqual(sphericCoord3));
        assertTrue(sphericCoord2.equals(sphericCoord3));
        assertEquals(sphericCoord2.hashCode(), sphericCoord3.hashCode());

        // With Different Parameter
        sphericCoord3.setRadius(0.0);
        assertFalse(sphericCoord2.isEqual(sphericCoord3));
        assertFalse(sphericCoord2.equals(sphericCoord3));
        sphericCoord3.setRadius(sphericCoord2.getRadius());

        sphericCoord3.setLatitude(0.0);
        assertFalse(sphericCoord2.equals(sphericCoord3));
        sphericCoord3.setLatitude(sphericCoord2.getLatitude());

        sphericCoord3.setLongitude(0.0);
        assertFalse(sphericCoord2.equals(sphericCoord3));
        sphericCoord3.setLongitude(sphericCoord2.getLongitude());
    }

    @Test
    public void testGetDistance() {
        //check distance to itself
        assertEquals(0.0, cartCoord1.getDistance(cartCoord1), 0.0);
        assertEquals(0.0, cartCoord2.getDistance(cartCoord2), 0.0);
        assertEquals(0.0, cartCoord3.getDistance(cartCoord3), 0.0);

        assertEquals(0.0, sphericCoord1.getDistance(sphericCoord1), 0.0);
        assertEquals(0.0, sphericCoord2.getDistance(sphericCoord2), 0.0);
        assertEquals(0.0, sphericCoord3.getDistance(sphericCoord3), 0.0);

        //check same distance in both directions
        assertEquals(cartCoord1.getDistance(cartCoord2), cartCoord2.getDistance(cartCoord1), MAX_DELTA);
        assertEquals(cartCoord2.getDistance(cartCoord3), cartCoord3.getDistance(cartCoord2), MAX_DELTA);

        assertEquals(sphericCoord1.getDistance(sphericCoord2), sphericCoord2.getDistance(sphericCoord1), MAX_DELTA);
        assertEquals(sphericCoord2.getDistance(sphericCoord3), sphericCoord3.getDistance(sphericCoord2), MAX_DELTA);

        //check correct distance
        assertEquals(15.514, cartCoord1.getDistance(cartCoord2), MAX_DELTA);
        assertEquals(4.292,  cartCoord2.getDistance(cartCoord3), MAX_DELTA);

        assertEquals(sphericCoord3.getRadius(), sphericCoord3.getDistance(new SphericCoordinate()), MAX_DELTA);

        //check invalid distance
        assertEquals(Double.POSITIVE_INFINITY, cartCoord1.getDistance(null), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, sphericCoord1.getDistance(null), 0.0);
    }


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


    @Test
    public void testCoordinateConversion() {
    	assertEquals(cartCoord2.asCartesianCoordinate(), cartCoord2);
        assertEquals(sphericCoord2.asSphericCoordinate(), sphericCoord2);

        assertEquals(cartCoord1.asSphericCoordinate().asCartesianCoordinate(), cartCoord1);
        assertEquals(cartCoord2.asSphericCoordinate().asCartesianCoordinate(), cartCoord2);
        assertEquals(cartCoord3.asSphericCoordinate().asCartesianCoordinate(), cartCoord3);

        assertEquals(sphericCoord1.asCartesianCoordinate().asSphericCoordinate(), sphericCoord1);
        assertEquals(sphericCoord2.asCartesianCoordinate().asSphericCoordinate(), sphericCoord2);
        assertEquals(sphericCoord3.asCartesianCoordinate().asSphericCoordinate(), sphericCoord3);
    }

} 