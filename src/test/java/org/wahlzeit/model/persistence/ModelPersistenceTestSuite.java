/*
 * Model Persistence Test Suite
 * 
 * ADAP CW-04
 *
 * 04.11.2017
 *
 * Copyright 2017
 * Part of Wahlzeit Application
 */

package org.wahlzeit.model.persistence;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	org.wahlzeit.model.persistence.AbstractAdapterTest.class,
	org.wahlzeit.model.persistence.DatastoreAdapterTest.class,
	org.wahlzeit.model.persistence.GcsAdapterTest.class
})


public class ModelPersistenceTestSuite {
	// do nothing
}
