package logic.publishAnAd;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.login.User;

public class PostDao {
	
	int currentId;
	
	public void createPost(String title, String body, String type) {
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("src/logic/File/post.json")) {			
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONObject ad = (JSONObject) obj;

			currentId = Integer.parseInt((String)ad.get("id"));
			
			FileWriter writer = new FileWriter("src/logic/File/post.json");
			
			ad.replace("id", Integer.toString(currentId), Integer.toString(currentId+1));
			
			JSONArray jSonPost = new JSONArray();
			jSonPost.add(title);
			jSonPost.add(body);
			jSonPost.add(type);
			
			ad.put(currentId, jSonPost);
			
			writer.write(ad.toJSONString());
			writer.close();
			
        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		catch (ParseException e) {
            e.printStackTrace();
        }
		
		return;
	}	
}
