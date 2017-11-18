/*
 * Shirt Photo Factory
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

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class ShirtPhotoFactory extends PhotoFactory{

	private static final Logger log = Logger.getLogger(ShirtPhotoFactory.class.getName());
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static ShirtPhotoFactory instance = null;
	
	/**
	 * @methodtype constructor
	 */
	public ShirtPhotoFactory() {
		super();
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized ShirtPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic ShirtPhotoFactory").toString());
			setInstance(new ShirtPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(ShirtPhotoFactory shirtPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = shirtPhotoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	@Override
	public ShirtPhoto createPhoto() {
		return new ShirtPhoto();
	}
	
	/**
	 * Creates a new photo with the specified id
	 */
	public ShirtPhoto createPhoto(PhotoId id) {
		return new ShirtPhoto(id);
	}



}
