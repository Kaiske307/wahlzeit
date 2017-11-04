/*
 * Wahlzeit Test Suite
 * 
 * ADAP CW-04
 *
 * 04.11.2017
 *
 * Copyright 2017
 * Part of Wahlzeit Application
 */

package org.wahlzeit;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	org.wahlzeit.handlers.HandlersTestSuite.class,
	org.wahlzeit.model.ModelTestSuite.class,
	org.wahlzeit.model.persistence.ModelPersistenceTestSuite.class,
	org.wahlzeit.services.ServicesTestSuite.class,
	org.wahlzeit.services.mailing.MailingTestSuite.class,
	org.wahlzeit.testEnvironmentProvider.EnvironmentProviderTestSuite.class,
	org.wahlzeit.utils.UtilsTestSuite.class
})

public class AllTestSuite {
	// do nothing
}
