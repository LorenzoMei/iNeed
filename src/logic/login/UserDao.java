package logic.login;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserDao {

	public static User findUsernameAndPassw(String username, String passw) {
		
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
	        System.out.println("File non trovato");
	        return null;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return user;
	}
	
	public static void createAccount(String username, String passw, String email, String city) {
		
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
