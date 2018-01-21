/**
 * 13.01.2018
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

import com.googlecode.objectify.annotation.Serialize;

/**	CREATION OF A NEW TYPE OBJECT
 * To create a new Shirt Type Object you need to create a new Instance of ShirtType.
 * This factory Method is called to create a new TypeObject with the given Attributes.
 * The Attributes are set in the Construcotr of the Class ShirtType with help of the ShirtManager.
 * The ShirtManager has its own createShirt() Method to create the new Instance of the TypeObject and manages them in a list.
 * This Factory Method is called by ShirtPhoto.initialize() and the Creation of a Photo is documented in this class.
 */


/**	SOLUTION SPACE
 * Delegation:		seperate-object
 * Selection:		on-the-spot
 * Configuration:	in-code
 * Instantiation:	in-code
 * Initialization:	default
 * Building:		default
 */

@Serialize
public class Shirt {

	protected ShirtType shirtType = null;	

	
	/**
	 * @methodtype constructor 
	 */
	public Shirt(ShirtType shirtType) {
		this.shirtType = shirtType;
	}

	/**
	 * @methodtype getter
	 */
	public ShirtType getType() {
		return shirtType;
	}

}
