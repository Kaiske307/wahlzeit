/**
 * 12.11.2017
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


public class ShirtPhotoTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
      // do nothing
	}

	@Test
	public void test() {
		ShirtPhoto pic1 = new ShirtPhoto("selfmade", "Retailer", "TShirt", 2012);
		PhotoId    id	= new PhotoId(10);
		ShirtPhoto pic2 = new ShirtPhoto(id, "impericon", "Internet", "Sweater", 2015);
		ShirtPhoto pic3 = new ShirtPhoto(id, "impericon", "Retailer", "TShirt",  2015);
		
        assertNotEquals(pic1.getId(), pic2.getId());
          
        assertEquals(pic2.getId(), pic3.getId());
	}

}
