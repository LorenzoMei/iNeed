package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Answer;

public class DAOAnswersSerialize extends DAOSerialize implements DAOAnswers{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOAnswersSerialize ref = null;
	
	public static DAOAnswersSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOAnswersSerialize();
		}
		
		return ref;
	}
	
	private DAOAnswersSerialize() {}
	
	public void loadAnswers(int id, String type, List<String> answersList) {
		
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(Integer.toString(id));
		
		File folder = new File("db/serialized/Answers/" + type);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].getName().contains(Integer.toString(id))) {
			  primaryKeyValues.add(listOfFiles[i].getName().substring(Integer.toString(id).length() + this.PRIMARY_KEY_VALUES_SEPARATOR.length()));
			  
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
			  primaryKeyValues.remove(listOfFiles[i].getName().substring(Integer.toString(id).length() + this.PRIMARY_KEY_VALUES_SEPARATOR.length()));
		  }
		}
	}
}
