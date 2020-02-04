package logic.checkanswersofanad;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.dao.DAOUserJson;

public class AnswersDao {
	
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	
	public Answers listOfAnswers(int id) {
		
        Answers list = new Answers();
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("src/logic/File/answers.json")) {			
			//Read JSON file
            Object answersObj = jsonParser.parse(reader);

            JSONObject answers = (JSONObject) answersObj;
            
            JSONArray listAnswers = (JSONArray) answers.get(Integer.toString(id));
            
            for(Object obj : listAnswers) {
            	list.setAnswersList((String)(obj));
            }
		}
		catch(IOException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		catch(ParseException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		catch(NullPointerException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	
		return list;
	}
}
