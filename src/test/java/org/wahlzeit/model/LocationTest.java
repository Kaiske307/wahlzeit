package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	private Location testLocation1;
	private Location testLocation2;
	private Location testLocation3;
	
	@Before
	public void setUp() throws Exception {
		testLocation1 = new Location(0.0, 1.0, 3.0);
		testLocation2 = new Location(0.0, 2.0, 4.0);
		testLocation3 = new Location(0.0, 2.0, 4.0);
	}

	@Test
	public void testLocation() {
//		assertEquals(testLocation2, testLocation3);
		assertNotEquals(testLocation1, testLocation3);
	}

}
