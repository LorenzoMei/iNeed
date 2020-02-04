package logic.dao;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import org.json.simple.*;
import org.json.simple.parser.*;

import logic.entity.User;

public class DAOUserJson implements DAOUser{
	
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	private String pathnameFile = "db/json/users/"; 

	private static DAOUserJson ref = null;
	
	public static DAOUserJson getReference() {
		
		if (ref == null) {
			ref = new DAOUserJson();
		}
		
		return ref;
	}
	
	private Method getGetterOrSetter(String getOrSet, String attrName, Object obj) throws NoSuchMethodException {
		
		Method[] methods = obj.getClass().getMethods();
		for (int j = 0; j < methods.length; j ++) {
			if (methods[j].getName().contains(getOrSet) && methods[j].getName().contains(attrName.substring(0, 1).toUpperCase() + attrName.substring(1))) {
				return methods[j];
			}
		}
		throw new NoSuchMethodException();
	}
	
	public User loadUser(String username, String passw) {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		File userDB = new File(pathnameFile + username + ".json");
		User user = null;
		try (FileReader reader = new FileReader(userDB)){
			JSONArray root = (JSONArray) JSONValue.parseWithException(reader);
			for (int i = 0; i < root.size(); i ++) {
				JSONObject currentNode = (JSONObject) root.get(i);
				String currentUsername = (String) currentNode.get("username");
				String currentPassw = (String) currentNode.get("passw");
				if (currentUsername.equals(username) && currentPassw.contentEquals(passw)) {
					user = new User();
					Field[] attributes = user.getClass().getDeclaredFields();
					for (int j = 0; j < attributes.length; j ++) {
						if (Modifier.isPrivate(attributes[j].getModifiers())) {
							this.getGetterOrSetter("set", attributes[j].getName(), user).invoke(user, currentNode.get(attributes[j].getName()));
						}
					}
				}
			}	
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "File " + pathnameFile + " not found");
		} catch (IOException | ParseException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		
		return user;		
	}
	
	public void storeUser(User user) {
		
//		It stores user data in a JSON array, coping values of all User attributes using reflection
//		TODO: 	for each user exist a dictionary node, and every node should be added to the array
//				root (or maybe we can write every user in his own file?), so an append write mode
//				should be implemented.
		
		File userDB = new File(pathnameFile + user.getUsername() + ".json");
		try (FileWriter writer = new FileWriter(userDB)) {
			JSONObject values = new JSONObject();
			JSONArray root = new JSONArray();
			Field[] attributes = user.getClass().getDeclaredFields();
						
			for (int i = 0; i < attributes.length; i ++) {
				if (Modifier.isPrivate(attributes[i].getModifiers())) {
					values.put(attributes[i].getName(), this.getGetterOrSetter("get", attributes[i].getName(), user).invoke(user, (Object[]) null));
				}
			}
			root.add(values);
			root.writeJSONString(writer);
			
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
