/**
 * Shirt Photo
 *
 * Version 1.0
 *
 * 2017/11/07
 *
 * Copyright (c) 2017 by Kai-Malte Böhling
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


import com.googlecode.objectify.annotation.Subclass;

/**	CREATION OF A NEW PHOTO
 * The creation of a new ShirtPhoto start with one of the Factory Methods createPhoto() in the ShirtPhotoFactory-Class.
 * The ShirtPhotoFactory is a Singleton. This createPhoto() Method is called by the PhotoUtil.createPhoto().
 * PhotoUtil.createPhoto() is called by the ShirtPhotoManager Class. This Class is the Entrypoints for new Photos.
 */

/**	SOLUTION SPACE
 * Delegation:		seperate-object
 * Selection:		sub-classing
 * Configuration:	in-code
 * Instantiation:	By-class-object
 * Initialization:	in-second-step
 * Building:		default
 */

@Subclass
public class ShirtPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Shirt shirt = null;

	
	/**
	 * Constants
	 */
	private static final String DEF_SHOP			= "Selfmade";
	private static final String DEF_SHOPTYPE		= "Webstore";
	private static final String DEF_SHIRTCUTTYPE	= "TShirt";
	private static final int 	DEF_YEAR			= 2000;
	
	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto() {
		super();
		this.initialize(DEF_SHOP, DEF_SHOPTYPE, DEF_SHIRTCUTTYPE, DEF_YEAR);
	}
	
	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto(PhotoId myId) {
		super(myId);
		this.initialize(DEF_SHOP, DEF_SHOPTYPE, DEF_SHIRTCUTTYPE, DEF_YEAR);
	}

	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto(String shop, String shopType, String shirtType, int year) {
		super();
		this.initialize(shop, shopType, shirtType, year);
	}
	
	/**
	 * @methodtype constructor
	 */
	public ShirtPhoto(PhotoId myId, String shop, String shopType, String shirtType, int year) {
		super(myId);
		this.initialize(shop, shopType, shirtType, year);
	}

	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto(ShirtType shirtType) {
		super();
		this.initialize(shirtType);
	}	
	
	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto(PhotoId myId, ShirtType shirtType) {
		super(myId);
		this.initialize(shirtType);
	}

	
	public void initialize(String shop, String shopType, String shirtType, int year) {
		ShirtType type = ShirtManager.getInstance().createShirtType(shop, shopType, shirtType, year);
		ShirtManager.getInstance().createShirt(type);
	}
	
	public void initialize(ShirtType type) {
		ShirtManager.getInstance().createShirt(type);
	}

}
