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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.wahlzeit.services.DataObject;

public class ShirtType extends DataObject{
	
	private static final long serialVersionUID = 1L;
	
	protected ShirtType superType = null;
	protected Set<ShirtType>subType = new HashSet<ShirtType>();
	
	protected String shop 			= ""; 	// Shop Name
	protected String shopType		= ""; 	// Shop Type (Webstire, Retailer etc)
	protected String shirtCutType	= ""; 	// Set Shirt Type (TShirt, VNeck, Shirt etc)
	protected int    year			= 0;	// The year the Shirt was bought

	/**
	 * @methodtype constructor
	 */
	public ShirtType(String shop, String shopType, String shirtType, int year) {
		this.shop			= shop;
		this.shopType		= shopType;
		this.shirtCutType 	= shirtType;
		this.year			= year;
	}
	
	/**
	 * @methodtype factory
	 */
	public Shirt createInstance() {
		return new Shirt(this);
	}
	
	/**
	 * @methodtype getter
	 */
	public ShirtType getSuperType() {
		return superType;
	}
	
	/**
	 *  @methodtype getter
	 */
	public boolean isSubType() {
		return superType != null;
	}
	
	/**
	 * @methodtype getter
	 */
	public Iterator<ShirtType> getSubTypeIterator() {
		return subType.iterator();
	}
	
	/**
	 * @methodtype setter
	 */
	public void addSubType(ShirtType shirtType) {
		assert (shirtType != null) : "tried to set null sub-type";
		shirtType.setSuperType(this);
		subType.add(shirtType);
	}

	/**
	 * @param shirtType
	 */
	private void setSuperType(ShirtType shirtType) {
		superType = shirtType;
	}
	
	/**
	 * @methodtype
	 */
	public boolean hasInstance(Shirt shirt) {
		assert (shirt != null) : "asked about null object";
		
		if (shirt.getType() == this) {
			return true;
		}
		
		for (ShirtType type : subType) {
			if (type.hasInstance(shirt)) {
				return true;
			}
		}
		
		return false;
	}
}
