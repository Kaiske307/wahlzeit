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

import java.util.ArrayList;
import java.util.List;

public class ShirtManager {

	private static ShirtManager instance = new ShirtManager();
	
	private List<Shirt> shirts = new ArrayList<>();
	private List<ShirtType> shirtTypes = new ArrayList<>();
	
	/**
	 * @methodtype constructor
	 */
	private ShirtManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @methodtype getter
	 */
	public static ShirtManager getInstance() {
		return instance;
	}

	/**
	 * @methodtype factory
	 */
	public Shirt createShirt(ShirtType shirtType) {
		Shirt shirt = shirtType.createInstance();
		
		if(shirts.contains(shirt)) {
			return shirts.get(shirts.indexOf(shirt));
		}
		else {
			shirts.add(shirt);
			return shirt;
		}
	}

	/**
	 * @methodtype factory
	 */
	public ShirtType createShirtType(String shop, String shopType, String shirtCutType, int year) {
		ShirtType shirtType = new ShirtType(shop, shopType, shirtCutType, year);
		
		if(shirtTypes.contains(shirtType)) {
			return shirtTypes.get(shirtTypes.indexOf(shirtType));
		}
		else {
			shirtTypes.add(shirtType);
			return shirtType;
		}
	}
}
