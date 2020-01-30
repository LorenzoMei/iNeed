package logic.checkAnswersOfAnAd;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AnswersDao {
	
	public static Answers listOfAnswers(int id) {
		
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
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			System.out.println("Errore. Post non trovato!");
		}
	
		return list;
	}
}
