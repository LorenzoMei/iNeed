package logic.dao;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.entity.User;

public class DAOUserJson  implements logic.dao.DAOUser{

	private static DAOUserJson ref = null;
	
	public static DAOUserJson getReference() {
		
		if (ref == null) {
			ref = new DAOUserJson();
		}
		
		return ref;
	}
	
	public User loadUser(String username, String passw) {
		
		User user = null;
		
		try {
			JSONParser parser = new JSONParser();
		
			JSONArray array = (JSONArray) parser.parse(new FileReader("src/logic/File/credential.json"));
			
			JSONObject obj = (JSONObject) array.get(0);
			String password = (String) obj.get(username);
			
			if(password.equals(passw))
				user = new User(username, passw);
		}
		catch (FileNotFoundException e) {
			// TODO print these using a logger
	        System.out.println("File non trovato");
	        return null;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return user;
	}
	
	public void storeUser(User user) {
		// TODO stub
	}
	
	private void createAccount(String username, String passw, String email, String city) {
		
// 		TODO this function should not be in this DAO. Instead, it should be in LoginController class, which is responsible to create a User newUser entity with the info of the new user.
//		Then, the entity will be sent to this DAO which will store it in DB if the account does not already exist.
		
		try {
			JSONParser parser = new JSONParser();
			
			FileReader reader = new FileReader("src/logic/File/credential.json");
			
            JSONArray array = (JSONArray) parser.parse(reader);
            JSONObject dict1 = (JSONObject) array.get(0);
            JSONObject dict2 = (JSONObject) array.get(1);
            
			FileWriter writer = new FileWriter("src/logic/File/credential.json");
			
			dict1.put(username, passw);
			
			JSONArray jSonInfo = new JSONArray();
			jSonInfo.add(email);
			jSonInfo.add(city);
			
			dict2.put(username, jSonInfo);
			
			writer.write(array.toJSONString());
			writer.close();
			
		}
		catch (FileNotFoundException e) {
	        System.out.println("File non trovato");
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
}
