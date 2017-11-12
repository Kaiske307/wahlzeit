/*
 * Shirt Photo Manager
 * 
 * Version 1.0
 * 
 * 2017/11/08
 * 
 * Copyright (c) Kai-Malte Böhling
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

//import java.util.logging.Logger;

import com.googlecode.objectify.annotation.Subclass;


@Subclass
public class ShirtPhotoManager extends PhotoManager{

//	Log not used in this Class
//	private static final Logger log = Logger.getLogger(ShirtPhotoManager.class.getName());
	
	protected static final ShirtPhotoManager instance = new ShirtPhotoManager();
	
	/**
	 * @methodtype constructor
	 */
	public ShirtPhotoManager() {
		super();
	}

}
