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
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.*;

import java.io.IOException;

public class ShirtPhotoManagerTest {

	@ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());
	
	/**
	 * @throws java.lang.Exception
	 */
	@Rule
    public final ExpectedException expException = ExpectedException.none();

	@Test
	public void test(){
		ShirtPhoto pic1 = new ShirtPhoto("selfmade", "Retailer", "TShirt", 2012);
		PhotoId    id	= new PhotoId(10);
		ShirtPhoto pic2 = new ShirtPhoto(id, "impericon", "Internet", "Sweater", 2015);
		ShirtPhoto pic3 = new ShirtPhoto(id, "impericon", "Retailer", "TShirt",  2015);
		
        PhotoManager manager = ShirtPhotoManager.getInstance(); 
    	
        assertNotNull(manager);

        try {
            manager.addPhoto(pic1);
            manager.addPhoto(pic2);
        } catch (IOException exception) {
            assertNull(exception);
        }
        assertTrue(manager.hasPhoto(pic1.id));
        assertTrue(manager.hasPhoto(pic2.id));
        assertNotNull(manager.getPhoto(pic1.id));
        assertNotNull(manager.getPhoto(pic2.id));

        try {
            expException.expect(IllegalStateException.class);
            manager.addPhoto(pic3);
        } catch (IOException exception) {
            assertNotNull(exception);
        }
        assertFalse(manager.hasPhoto(pic3.id));
        assertNull(manager.getPhoto(pic3.id));
	}

}
