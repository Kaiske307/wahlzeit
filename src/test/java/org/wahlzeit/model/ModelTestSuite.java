/*
 * Model Test Suite
 * 
 * Version 1.0
 * 
 * 2017/11/04
 *
 * Copyright (c) 2017 by Kai-Malte B�hling
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

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	org.wahlzeit.model.AccessRightsTest.class,
	org.wahlzeit.model.CartesianCoordinateTest.class,
	org.wahlzeit.model.FlagReasonTest.class,
	org.wahlzeit.model.GenderTest.class,
	org.wahlzeit.model.GuestTest.class,
	org.wahlzeit.model.LocationTest.class,
	org.wahlzeit.model.PhotoFilterTest.class,
	org.wahlzeit.model.ShirtPhotoTest.class,
	org.wahlzeit.model.ShirtPhotoFactoryTest.class,
	org.wahlzeit.model.ShirtPhotoManagerTest.class,
	org.wahlzeit.model.SphericCoordinateTest.class,
	org.wahlzeit.model.TagsTest.class,
	org.wahlzeit.model.UserStatusTest.class,
	org.wahlzeit.model.ValueTest.class
})

public class ModelTestSuite {
	// do nothing
}
