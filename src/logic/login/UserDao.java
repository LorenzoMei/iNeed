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
		
			JSONArray array = (JSONArray) parser.parse(new FileReader("src/logic/credential.json"));

			for (Object obj : array){
				JSONObject credential = (JSONObject) obj;

				String password = (String) credential.get(username);
				if(password.equals(passw))
					user = new User(username, passw);
			}
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
}
