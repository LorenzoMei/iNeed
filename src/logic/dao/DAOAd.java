package logic.dao;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.entity.Ad;

public class DAOAd extends DAOJson{
	
	int currentId;
	
	Logger logger = Logger.getLogger(DAOAd.class.getName());
	
	public void storeAd(Ad ad) {
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("src/logic/File/post.json"); FileWriter writer = new FileWriter("src/logic/File/post.json")) {			
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONObject root = (JSONObject) obj;

			currentId = Integer.parseInt((String)root.get("id"));
			
			root.replace("id", Integer.toString(currentId), Integer.toString(currentId+1));
			
			JSONArray jSonPost = new JSONArray();
			jSonPost.add(ad.getUser().getUsername());
			jSonPost.add(ad.getTitle());
			jSonPost.add(ad.getBody());
			jSonPost.add(ad.getType());
			jSonPost.add(ad.getData());
			
			root.put(currentId, jSonPost);
			
			writer.write(root.toJSONString());
        } 
		catch (IOException | ParseException e) {
			logger.log(Level.SEVERE, e.toString());
        } 
	}	
}
