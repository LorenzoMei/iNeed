package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Answer;

public class DAOAnswerSerialize extends DAOSerialize implements DAOAnswer{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOAnswerSerialize ref = null;
	
	public static DAOAnswerSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOAnswerSerialize();
		}
		
		return ref;
	}
	
	private DAOAnswerSerialize() {}
	
	public void loadAnswers(int id, String type, List<String> answersList) {
		
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(Integer.toString(id));
		
		File folder = new File("db/serialized/Answer");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].getName().contains(Integer.toString(id)) && listOfFiles[i].getName().contains(type)) {
			  String secondValueOfPrimaryKey = listOfFiles[i].getName().substring(Integer.toString(id).length() + PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - PRIMARY_KEY_VALUES_SEPARATOR.length() - SERIALIZED_EXTENSION.length());
			  primaryKeyValues.add(secondValueOfPrimaryKey);
			  
			  Answer answers = new Answer();
			  try {
				  this.load(answers, primaryKeyValues);
			  } catch (ElementInDBNotFoundException e) {
				  logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
			  	} 
			  catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
					logger.log(Level.SEVERE, e.toString());
			  } 
			  
			  answersList.add(answers.getUsername());
			  primaryKeyValues.remove(secondValueOfPrimaryKey);
		  }
		}
	}
	
	public void storeAnswers(Answer answers) {
		
		List<String> primaryKeyNames = new ArrayList<>();
		primaryKeyNames.add("id");
		primaryKeyNames.add("username");
		primaryKeyNames.add("type");
		
		try {
			this.store(answers, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}
}
