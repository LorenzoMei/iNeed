package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Answer;
import logic.entity.Favor;

public class DAOAnswerSerialize extends DAOSerialize implements DAOAnswer{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOAnswerSerialize ref = null;
	
	public static DAOAnswerSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOAnswerSerialize();
		}
		File storeFolder = new File (ref.readDBPath() + Answer.class.getSimpleName());
		if (!storeFolder.exists()) {
			storeFolder.mkdirs();
		}
		return ref;
	}
	
	private DAOAnswerSerialize() {}
	
	@Override
	public void loadAnswers(int id, String type, List<Answer> answersList) {
		
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(Integer.toString(id));
		
		File folder = new File("db/serialized/Answer");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].getName().contains(Integer.toString(id)) && listOfFiles[i].getName().contains(type)) {
			  String secondValueOfPrimaryKey = listOfFiles[i].getName().substring(Integer.toString(id).length() + PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - PRIMARY_KEY_VALUES_SEPARATOR.length() - SERIALIZED_EXTENSION.length());
			  primaryKeyValues.add(secondValueOfPrimaryKey);
			  
			  Answer answer = new Answer();
			  try {
				  this.load(answer, primaryKeyValues);
			  } catch (ElementInDBNotFoundException e) {
				  logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
			  	} 
			  catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
					logger.log(Level.SEVERE, e.toString());
			  } 
			  
			  answersList.add(answer);
			  primaryKeyValues.remove(secondValueOfPrimaryKey);
		  }
		}
	}
	
	public void storeAnswer(Answer answer) {
		
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(String.format("%d", answer.getId()));
		primaryKeyValues.add(answer.getUsername());
		primaryKeyValues.add(answer.getType());
		
		try {
			this.store(answer, primaryKeyValues);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}

	@Override
	public void loadAnswer(int id, String type, String username, Answer answer) throws AnswerNotFoundException {
		List<String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(String.format("%d", id));
		primaryKeyValues.add(username);
		primaryKeyValues.add(type);
		
		try {
			this.load(answer, primaryKeyValues);
		} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException | IOException e) {
			logger.log(Level.SEVERE, e.toString());
		} catch (ElementInDBNotFoundException e) {
			throw new AnswerNotFoundException(e);
		}
		
	}
	
}
