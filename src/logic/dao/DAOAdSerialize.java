package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Ad;
import logic.misc.ReflectionMiscellaneous;

public class DAOAdSerialize extends DAOSerialize implements DAOAd{
	
	public static final String LASTID_TYPE_OFFER = "OfferAd";
	public static final String LASTID_TYPE_REQUEST  = "RequestAd";
	public static final int LASTID_ZEROID = -1;
	
	Logger logger = Logger.getLogger(DAOAdSerialize.class.getName());
	private static DAOAdSerialize ref = null;
	
	private DAOAdSerialize() {
//		p
		logger.log(Level.INFO, "initializing " + this.getClass().getSimpleName());
		
		List<Field> supportedAds = ReflectionMiscellaneous.getSupported(this, "LASTID_TYPE");
		
		for (int i = 0; i < supportedAds.size(); i ++) {
			try {
				String pathLastId = this.readDBPath() + "AdId" + File.separator + supportedAds.get(i).get(null).toString();
//				p
				String toLog = String.format("checking if %s directory exists", pathLastId);
				logger.log(Level.INFO, toLog);
				File lastIdFile = new File(pathLastId);
				if (!lastIdFile.exists()) {
//					p
					toLog = String.format("%s does not exists, creating it", pathLastId);
					logger.log(Level.INFO, toLog);
					AdId zeroId = new AdId();
					zeroId.setLastId(LASTID_ZEROID);
					zeroId.setType(supportedAds.get(i).get(null).toString());
					this.storeLastId(zeroId);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.log(Level.SEVERE, e.toString() + "unable to initialize DAOAd");
			}
		}
	}
	
	public static DAOAdSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOAdSerialize();
		}
		return ref;
	}
	
	@Override
	public void loadAd(Ad ad, int id) throws AdNotFoundException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		List <String> primaryKeyValues = new ArrayList <>();
		primaryKeyValues.add(Integer.toString(id));
		
		try {
			this.load(ad, primaryKeyValues);
		} catch (ElementInDBNotFoundException e) {
			throw new AdNotFoundException(e);
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
			logger.log(Level.SEVERE, e.toString());
		} 
	}
	
	@Override
	public int storeNewAd(Ad ad) {
		AdId id = new AdId();
		this.updateLastId(ad.getClass().getSimpleName());
		this.loadLastId(id, ad.getClass().getSimpleName());
		ad.setId(id.getLastId());
//		p
		logger.log(Level.INFO, "ad " + ad + " id set to " + ad.getId());
		this.storeAd(ad);
		return ad.getId();
	}
	
	@Override
	public void storeAd(Ad ad) {
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(String.format("%d", ad.getId()));
		try {
			this.store(ad, primaryKeyValues);
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		 
	}
	
	private void storeLastId(AdId id) {
		List<String> primaryKeyNames = new ArrayList<>();
		primaryKeyNames.add(id.getType());
		try {
			this.store(id, primaryKeyNames);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	private int updateLastId(String type) {
		
		int oldVal;
		int newVal;
		AdId lastId = new AdId();
		
		this.loadLastId(lastId, type);
//		p
		logger.log(Level.INFO, "old lastId is " + lastId.getLastId());
		oldVal = lastId.getLastId();
		newVal = ++ oldVal ;
		lastId.setLastId(newVal);
//		p
		logger.log(Level.INFO, "storing new val of lastId: " + lastId.getLastId());
		this.storeLastId(lastId);
		
		return newVal;
	}
	
	private void loadLastRequestId(AdId id) {
		this.loadLastId(id, DAOAdSerialize.LASTID_TYPE_REQUEST);
	}
	
	private void loadLastOfferId(AdId id) {
		this.loadLastId(id, DAOAdSerialize.LASTID_TYPE_OFFER);
	}
	
	private void loadLastId(AdId id, String type) {
		
		List <String> primaryKeyValues = new ArrayList <>();
		primaryKeyValues.add(type);
		
		try {
			this.load(id, primaryKeyValues);
		} catch (ElementInDBNotFoundException e) {
			logger.log(Level.INFO, "file" + e.getPath() + " not found");
			logger.log(Level.INFO, "create file " + e.getPath());
		} catch (IOException | IllegalArgumentException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
			logger.log(Level.SEVERE, e.toString());
		} 
	}

	@Override
	public int getLastRequestId() {
		AdId lastRequestId = new AdId();
		this.loadLastRequestId(lastRequestId);
		
		return lastRequestId.getLastId();
	}

	@Override
	public int getLastOfferId() {
		AdId lastOfferId = new AdId();
		this.loadLastOfferId(lastOfferId);
		
		return lastOfferId.getLastId();
	}
}