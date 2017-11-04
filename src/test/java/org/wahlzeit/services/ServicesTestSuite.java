/*
 * Services Test Suite
 * 
 * ADAP CW-04
 *
 * 04.11.2017
 *
 * Copyright 2017
 * Part of Wahlzeit Application
 */

package org.wahlzeit.services;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	org.wahlzeit.services.EmailAddressTest.class,
	org.wahlzeit.services.LogBuilderTest.class
})

public class ServicesTestSuite {
	// do nothing
}
