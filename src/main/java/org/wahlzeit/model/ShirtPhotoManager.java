/**
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

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.Persistent;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Subclass;

import com.google.appengine.api.images.Image;
import org.wahlzeit.utils.DesignPattern;

@DesignPattern(
	patternName = "Singleton",
	patternParticipants = {"PhotoManager", "ShirtPhotoManager"}
	)

@Subclass
public class ShirtPhotoManager extends PhotoManager{

	private static final Logger log = Logger.getLogger(ShirtPhotoManager.class.getName());

	protected static ShirtPhotoManager instance = new ShirtPhotoManager();

	/**
	* In-memory cache for photos
	*/
	protected Map<PhotoId, ShirtPhoto> photoCache = new HashMap<>();

	/**
	 * @methodtype constructor
	 */
	public ShirtPhotoManager() {
		super();
	}

	/**
		 *
		 */
		@Override
		public ShirtPhoto getPhotoFromId(PhotoId id) {
			// Assertion DbC
			if (id == null) {
				return null;
			}

			ShirtPhoto result = doGetPhotoFromId(id);

			return result;
		}

		/**
		 * @methodtype get
		 * @methodproperties primitive
		 */
		@Override
		protected ShirtPhoto doGetPhotoFromId(PhotoId id) {
			// Assertion DbC
			if (id == null) {
				throw new IllegalArgumentException("PhotoId cannot be null");
			}

			return photoCache.get(id);
		}

		/**
		 * @methodtype command
		 * @methodproperties primitive
		 */
		protected void doAddPhoto(ShirtPhoto myPhoto) {
			// Assertion DbC
			if (myPhoto == null) {
				throw new IllegalArgumentException("Cannot add Null Object as Photo");
			}

			photoCache.put(myPhoto.getId(), myPhoto);
		}

		/**
		 * @methodtype command
		 *
		 * Load all persisted photos. Executed when Wahlzeit is restarted.
		 */
		@Override
		public void loadPhotos() {
			Collection<ShirtPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<ShirtPhoto>>() {
				@Override
				public Collection<ShirtPhoto> run() {
					Collection<ShirtPhoto> existingPhotos = new ArrayList<>();
					readObjects(existingPhotos, ShirtPhoto.class);
					return existingPhotos;
				}
			});

			for (ShirtPhoto photo : existingPhotos) {
				if (!doHasPhoto(photo.getId())) {
					log.config(LogBuilder.createSystemMessage().
							addParameter("Load ShirtPhoto with ID", photo.getIdAsString()).toString());
					loadScaledImages(photo);
					doAddPhoto(photo);
				} else {
					log.config(LogBuilder.createSystemMessage().
							addParameter("Already loaded ShirtPhoto", photo.getIdAsString()).toString());
				}
			}

			log.info(LogBuilder.createSystemMessage().addMessage("All ShirtPhotos loaded.").toString());
		}

		/**
		 * @methodtype boolean-query
		 * @methodproperty primitive
		 */
		protected boolean doHasPhoto(PhotoId id) {
			// Assertion DbC
			if (id == null) {
				return false;
			}

			return photoCache.containsKey(id);
		}

		@Override
		protected void updateDependents(Persistent obj) {
			if (obj instanceof ShirtPhoto) {
				ShirtPhoto photo = (ShirtPhoto) obj;
				saveScaledImages(photo);
				updateTags(photo);
				UserManager userManager = UserManager.getInstance();
				Client owner = userManager.getClientById(photo.getOwnerId());
				userManager.saveClient(owner);
			}
		}

		/**
		 *
		 */
		@Override
		public void savePhotos() throws IOException{
			updateObjects(photoCache.values());
		}

		/**
	 * @methodtype get
	 */
	@Override
	public Map<PhotoId, ShirtPhoto> getPhotoCache() {
		return photoCache;
	}

		/**
		 *
		 */
		@Override
		public Set<ShirtPhoto> findPhotosByOwner(String ownerName) {
			Set<ShirtPhoto> result = new HashSet<>();
			readObjects(result, ShirtPhoto.class, Photo.OWNER_ID, ownerName);

			for (ShirtPhoto photo : result) {
				doAddPhoto(photo);
			}

			return result;
		}

		/**
		 *
		 */
		@Override
		public ShirtPhoto getVisiblePhoto(PhotoFilter filter) {
			filter.generateDisplayablePhotoIds();
			return getPhotoFromId(filter.getRandomDisplayablePhotoId());
		}

		/**
		 *
		 */
		@Override
		public ShirtPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
			PhotoId id = PhotoId.getNextId();
			ShirtPhoto result = (ShirtPhoto) PhotoUtil.createPhoto(filename, id, uploadedImage);
			addPhoto(result);
			return result;
		}

		/**
		 * @methodtype command
		 */
		public void addPhoto(ShirtPhoto photo) throws IOException {
			PhotoId id = photo.getId();
			assertIsNewPhoto(id);
			doAddPhoto(photo);

			GlobalsManager.getInstance().saveGlobals();
		}

}
