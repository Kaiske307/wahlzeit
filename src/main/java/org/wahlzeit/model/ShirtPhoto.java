/*
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

import java.time.Year;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class ShirtPhoto extends Photo {

	/**
	 * Constants
	 */
	private static final String DEF_SHOP 		= "Selfmade";
	private static final String DEF_SHOPTYPE 	= "Webstore";
	private static final String DEF_SHIRTTYPE 	= "TShirt";
	private static final int 	DEF_YEAR 		= 2000;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String shop 		= ""; 	// Shop Name
	protected String shopType 	= ""; 	// Shop Type (Webstire, Retailer etc)
	protected String shirtType 	= ""; 	// Set Shirt Type (TShirt, VNeck, Shirt etc)
	protected int    year		= 0;	// The year the Shirt was bought
		
	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto() {
		super();
		this.initialize(DEF_SHOP, DEF_SHOPTYPE, DEF_SHIRTTYPE, DEF_YEAR);
	}
	
	/**
	 * @methodtype Constructor
	 */
	public ShirtPhoto(PhotoId myId) {
		super(myId);
		this.initialize(DEF_SHOP, DEF_SHOPTYPE, DEF_SHIRTTYPE, DEF_YEAR);
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
	
	public void initialize(String shop, String shopType, String shirtType, int year) {
		this.shop 		= shop;
		this.shopType 	= shopType;
		this.shirtType 	= shirtType;
		this.year		= year;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setShirtType(String shirtType) {
		this.shirtType = shirtType;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setYear(int year) {
		// Assertion DbC
		// Year can be at maximum current Year
		if (year <= Year.now().getValue()) {
			this.year = year;
		}
		else {
			this.year = DEF_YEAR;
		}
	}
	
	/**
	 * @methodtype getter
	 */
	public String getShop() {
		return shop;
	}
	
	/**
	 * @methodtype getter
	 */
	public String getShopType() {
		return shopType;
	}
	
	/**
	 * @methodtype getter
	 */
	public String getShirtType() {
		return shirtType;
	}
	
	/**
	 * @methodtype getter
	 */
	public int getYear() {
		return year;
	}
}
