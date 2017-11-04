package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// Test Cases for Coordinate Class

public class CoordinateTest {

	public static Coordinate test1;
	public static Coordinate test2;
	public static Coordinate test3;
	
	@Before
	public void setUp() throws Exception {
		test1 = new Coordinate(0.0, 1.0, 3.0);
		test2 = new Coordinate(0.0, 2.0, 4.0);
		test3 = new Coordinate(0.0, 2.0, 4.0);
	}

	
	@Test
    public void isEqual() throws Exception {
        assertEquals(test2,test3);
        assertNotEquals(test1,test2);
    }

} 