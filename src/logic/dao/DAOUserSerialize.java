package logic.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.User;

public class DAOUserSerialize extends DAOSerialize implements DAOUser{

	Logger logger = Logger.getLogger(DAOUserJson.class.getName());

	private static DAOUserSerialize ref = null;
	
	public static DAOUserSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOUserSerialize();
		}
		
		return ref;
	}
	
	public void loadUser(User user, String username) throws UserNotFoundException{
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		
		List <String> primaryKeyNames = new ArrayList <String>();
		primaryKeyNames.add("username");
		List <String> primaryKeyValues = new ArrayList <String>();
		primaryKeyValues.add(username);
		
		try {
			this.load(user, primaryKeyNames, primaryKeyValues);
		} catch (ElementInDBNotFoundException e) {
			logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
			throw new UserNotFoundException();
		} 
		
		catch (IOException | IllegalArgumentException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		} 
	}
	
	public void storeUser(User user) {
		
//		It stores user data in a JSON array, coping values of all User attributes using reflection
		
		List<String> primaryKeyNames = new ArrayList<String>();
		primaryKeyNames.add("username");
		try {
			this.store(user, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		 
	}

}
