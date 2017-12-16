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

/**
 * @author kaibo
 *
 */
public class CartesianCoordinateTest {
	private static final double MAX_DELTA = 1E-10;
	
	private CartesianCoordinate cartCoord1;
	private CartesianCoordinate cartCoord2;
	private CartesianCoordinate cartCoord3;
	private CartesianCoordinate cartCoord3_1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cartCoord1   = new CartesianCoordinate();
		cartCoord2   = new CartesianCoordinate(10.6, 8.4, 7.6);
		cartCoord3   = new CartesianCoordinate(8.3, 6.7, 4.4);
		cartCoord3_1 = new CartesianCoordinate(8.3, 6.7, 4.4);

	}

	/**
	 * Test Methods for Constructors
	 */
	@Test
	public void testConstructors() {
		assertNotNull(cartCoord1);
		assertNotNull(cartCoord2);
		assertNotNull(cartCoord3);
		assertNotNull(cartCoord3_1);
		
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
		
		assertEquals(8.3, cartCoord3_1.getX(), MAX_DELTA);
		assertEquals(6.7, cartCoord3_1.getY(), MAX_DELTA);
		assertEquals(4.4, cartCoord3_1.getZ(), MAX_DELTA);
	}
	
	/**
	 * Test Methods for Cartesian Equals
	 */
	@Test
	public void testCartesianEquals() {
		// Null Check
		assertFalse(cartCoord1.equals(null));
		assertFalse(cartCoord1.isEqual(null));
		assertFalse(cartCoord1.equals(new Object()));

		// Getter Test
		assertFalse(cartCoord2.equals(cartCoord3));

		assertTrue(cartCoord3.isEqual(cartCoord3_1));
		assertTrue(cartCoord3.equals(cartCoord3_1));
	}
	
	/**
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#asCartesianCoordinate()}.
	 * Test method for {@link org.wahlzeit.model.SphericCoordinate#asSphericCoordinate()}.
	 */
	@Test
	public void testCoordinateTransformation() {
		assertEquals(cartCoord2.asCartesianCoordinate(), cartCoord2);

		assertEquals(cartCoord1.asSphericCoordinate().asCartesianCoordinate(), cartCoord1);
		assertEquals(cartCoord2.asSphericCoordinate().asCartesianCoordinate(), cartCoord2);
		assertEquals(cartCoord3.asSphericCoordinate().asCartesianCoordinate(), cartCoord3);

	}

	/**
	 * Test method for {@link org.wahlzeit.model.CartesianCoordinate#getDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testGetDistance() {
		//check distance to itself
		assertEquals(0.0, cartCoord1.getDistance(cartCoord1), 0.0);
		assertEquals(0.0, cartCoord2.getDistance(cartCoord2), 0.0);
		assertEquals(0.0, cartCoord3.getDistance(cartCoord3), 0.0);

		//check same distance in both directions
		assertEquals(cartCoord1.getDistance(cartCoord2), cartCoord2.getDistance(cartCoord1), MAX_DELTA);
		assertEquals(cartCoord2.getDistance(cartCoord3), cartCoord3.getDistance(cartCoord2), MAX_DELTA);

		//check correct distance
		assertEquals(15.514, cartCoord1.getDistance(cartCoord2), 0.1);
		assertEquals(4.292,  cartCoord2.getDistance(cartCoord3), MAX_DELTA);


		//check invalid distance
		assertEquals(Double.POSITIVE_INFINITY, cartCoord1.getDistance(null), 0.0);
	}

	/**
	 * Test method for {@link org.wahlzeit.model.CartesianCoordinate#getCartesianDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testGetCartesianDistance() {
		assertEquals(cartCoord1.getCartesianDistance(cartCoord2), cartCoord2.getCartesianDistance(cartCoord1), MAX_DELTA);
		assertEquals(cartCoord1.getCartesianDistance(cartCoord3), cartCoord3.getCartesianDistance(cartCoord1), MAX_DELTA);
		assertEquals(cartCoord2.getCartesianDistance(cartCoord3), cartCoord3.getCartesianDistance(cartCoord2), MAX_DELTA);
	}

	/**
	 * Test method for {@link org.wahlzeit.model.CartesianCoordinate#getSphericDistance(org.wahlzeit.model.Coordinate)}.
	 */
	@Test
	public void testGetSphericDistance() {
		double distC1_C2 = cartCoord1.asSphericCoordinate().getSphericDistance(cartCoord2); 
		double distC2_C1 = cartCoord2.asSphericCoordinate().getSphericDistance(cartCoord1); 
		
		assertEquals(distC1_C2, cartCoord1.getCartesianDistance(cartCoord2), MAX_DELTA);
		assertEquals(distC2_C1, cartCoord2.getCartesianDistance(cartCoord1), MAX_DELTA);
	}

}
