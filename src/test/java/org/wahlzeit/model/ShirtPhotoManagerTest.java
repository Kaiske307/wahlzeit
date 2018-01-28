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

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;


import java.io.IOException;

public class ShirtPhotoManagerTest {

	@ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());
	
	private ShirtPhotoManager shirtPhotoManager;

	private PhotoId photoId;
	private ShirtPhoto shirtPhoto;

	@Before
	public void setup() {
		this.shirtPhotoManager = new ShirtPhotoManager();
		this.photoId = new PhotoId(42);
		this.shirtPhoto = new ShirtPhoto(photoId);
	}

	@Test
	public void testGetPhotoFromNullId() {
		ShirtPhoto shirtPhoto = shirtPhotoManager.getPhotoFromId(null);
		assertNull(shirtPhoto);
	}

	@Test
	public void testGetPhotoFromId() {
		shirtPhotoManager.photoCache.put(photoId, shirtPhoto);

		ShirtPhoto testShirtPhoto = shirtPhotoManager.getPhotoFromId(photoId);
		assertNotNull(testShirtPhoto);
		assertEquals(shirtPhoto, testShirtPhoto);
	}

	@Test
	public void testDoAddPhoto() {
		shirtPhotoManager.doAddPhoto(shirtPhoto);

		ShirtPhoto testShirtPhoto = shirtPhotoManager.photoCache.get(photoId);
		assertNotNull(testShirtPhoto);
		assertEquals(shirtPhoto, testShirtPhoto);
	}

	@Test
	public void testDoHasPhoto() {
		assertFalse(shirtPhotoManager.doHasPhoto(photoId));
		shirtPhotoManager.photoCache.put(photoId, shirtPhoto);
		assertTrue(shirtPhotoManager.doHasPhoto(photoId));
	}

}
