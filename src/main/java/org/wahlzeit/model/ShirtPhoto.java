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

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class ShirtPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String shop 		= ""; 	// Set Shop only once
	protected String shopType 	= ""; 	// Set Shop Type (Internet, Retailer etc) only once
	protected String shirtType 	= ""; 	// Set Shirt Type (TShirt, VNeck, Sweater etc) only once
	protected int    year		= 0;	// The year the Shirt was bought
		
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
