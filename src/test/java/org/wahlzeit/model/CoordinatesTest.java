package org.wahlzeit.model;

import org.junit.before;
import org.junit.after;
import static org.junit.Assert.assertEquals;

// Test Cases for Coordinates Class

public class CoordinatesTests {
	private Coordinates coords;
	private final static double DELTA = 0.1;
	
	@Before
	public void setUp() {
		coords = new Coordinates(3.4, 4.9);
	}
	
	public void getSetTest() {
		double x = 22.39;
		double y = 47.74;
		
		coords.setX(x);
		coords.setY(y);
		
		assertEquals(x, coords.getX(), DELTA);
		assertEquals(y, coords.getY(), DELTA);
	}
	
	@Test
	public void testXDistance() {
		Coordinates testCoord = new Coordinates (20.4, 38.5);
		double distance = 17.0;
		
		assertEquals(distance, testCoord.getXDistance(testCoord), DELTA);
	}
	
	public void testYDistance() {
		Coordinates testCoord = new Coordinates (20.4, 21,9);
		double distance = 17.0;
		
		assertEquals(distance, testCoord.getXDistance(testCoord), DELTA);
	}
	
	public void testDistance() {
		Coordinates testCoord = new Coordinate(10.0,10.0);
		
		Coordinates distCoord = coords.getDistance(testCoord);

		assertEquals(6.6,distCoord.getX(),DELTA);
		assertEquals(5.1,distCoord.getY(),DELTA);
	}
}