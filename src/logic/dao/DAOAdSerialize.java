package logic.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Ad;
import logic.entity.AdId;

public class DAOAdSerialize extends DAOSerialize implements DAOAd{
	
	Logger logger = Logger.getLogger(DAOAdSerialize.class.getName());
	
	private static DAOAdSerialize ref = null;
	
	public static DAOAdSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOAdSerialize();
		}
		
		return ref;
	}
	
	public void loadAd(Ad ad, int id) throws AdNotFoundException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		
		List <String> primaryKeyNames = new ArrayList <String>();
		primaryKeyNames.add("id");
		List <String> primaryKeyValues = new ArrayList <String>();
		primaryKeyValues.add(Integer.toString(id));
		
		try {
			this.load(ad, primaryKeyNames, primaryKeyValues);
		} catch (ElementInDBNotFoundException e) {
			logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
			throw new AdNotFoundException();
		} 
		
		catch (IOException | IllegalArgumentException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		} 
	}
	
	public void storeAd(Ad ad) {
		List<String> primaryKeyNames = new ArrayList<String>();
		primaryKeyNames.add("id");
		try {
			this.store(ad, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		 
	}
	
	public void loadId(AdId id) {
		
		List <String> primaryKeyNames = new ArrayList <String>();
		primaryKeyNames.add("name");
		List <String> primaryKeyValues = new ArrayList <String>();
		primaryKeyValues.add("lastId");
		
		try {
			this.load(id, primaryKeyNames, primaryKeyValues);
		} catch (ElementInDBNotFoundException e) {
			logger.log(Level.INFO, "file" + e.getPath() + " not found");
			logger.log(Level.INFO, "create file " + e.getPath());
			
			try {
				this.store(id, primaryKeyNames);
			} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | IOException ex) {
				logger.log(Level.SEVERE, "cannot create file " + e.getPath());
			}
		} 
		
		catch (IOException | IllegalArgumentException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		} 
	}
}
