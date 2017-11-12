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
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class ShirtPhotoFactoryTest {

	@ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());
	

	@Test
	public void test() {
		ShirtPhotoFactory instance = ShirtPhotoFactory.getInstance();

        assertNotNull(instance);
	
		ShirtPhoto pic1 = instance.createShirtPhoto("selfmade", "Retailer", "TShirt", 2015);
		PhotoId    id	= new PhotoId(10);
		ShirtPhoto pic2 = instance.createShirtPhoto(id, "impericon", "Internet", "Sweater", 2012);
		ShirtPhoto pic3 = instance.createShirtPhoto(id, "impericon", "Internet", "TShirt",  2015);
	        
        assertNotNull(pic1);
        assertNotNull(pic2);
        assertNotNull(pic3);
        assertEquals(pic1.getClass(), pic2.getClass());
        assertEquals(pic2.getClass(), pic3.getClass());
	}

}
