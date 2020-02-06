package logic.dao;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import org.json.simple.*;
import org.json.simple.parser.*;

import logic.entity.User;

public class DAOUserJson extends DAOJson implements DAOUser{
	
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	private String pathnameFile = "db/json/User/"; 

	private static DAOUserJson ref = null;
	
	public static DAOUserJson getReference() {
		
		if (ref == null) {
			ref = new DAOUserJson();
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
		
		catch (IOException | ParseException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.log(Level.SEVERE, e.toString());
		}	
	}
	
	public void storeUser(User user) {
		
//		It stores user data in a JSON array, coping values of all User attributes using reflection
		
		List<String> primaryKeyNames = new ArrayList<String>();
		primaryKeyNames.add("user");
		try {
			this.store(user, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		 
	}
	
	public void createAccount(String username, String passw, String email, String city) {
		
// 		TODO 	this function should not be in this DAO. Instead, it should be in LoginController class, which is responsible to create a User newUser entity with the info of the new user.
//			 	Then, the entity will be sent to this DAO which will store it in DB if the account does not already exist.
		
		try (FileReader reader = new FileReader(pathnameFile); FileWriter writer = new FileWriter(pathnameFile)) {
			
			JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(reader);
            JSONObject dict1 = (JSONObject) array.get(0);
            JSONObject dict2 = (JSONObject) array.get(1);
            
			dict1.put(username, passw);
			
			JSONArray jSonInfo = new JSONArray();
			jSonInfo.add(email);
			jSonInfo.add(city);
			
			dict2.put(username, jSonInfo);
			
			writer.write(array.toJSONString());			
		}
		catch (IOException | ParseException e) {
			logger.log(Level.SEVERE, "ERRORE IN DAOUserJson.java NEL METODO createAccount()");
	    }
	}
}
