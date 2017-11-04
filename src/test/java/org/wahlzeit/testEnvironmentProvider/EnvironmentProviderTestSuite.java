/*
 * Environment Provider Test Suite
 * 
 * ADAP CW-04
 *
 * 04.11.2017
 *
 * Copyright 2017
 * Part of Wahlzeit Application
 */

package org.wahlzeit.testEnvironmentProvider;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider.class,
	org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider.class,
	org.wahlzeit.testEnvironmentProvider.SysConfigProvider.class,
	org.wahlzeit.testEnvironmentProvider.UserServiceProvider.class,
	org.wahlzeit.testEnvironmentProvider.UserSessionProvider.class,
	org.wahlzeit.testEnvironmentProvider.WebFormHandlerProvider.class
})

public class EnvironmentProviderTestSuite {
	// do nothing
}
