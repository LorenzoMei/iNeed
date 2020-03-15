package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Review;

public class DAOReviewSerialize extends DAOSerialize implements DAOReviews{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOReviewSerialize ref = null;
	
	public static DAOReviewSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOReviewSerialize();
		}
		
		return ref;
	}
	
	private DAOReviewSerialize() {}
	
	public void storeReview(Review review) {
		
		List<String> primaryKeyNames = new ArrayList<>();
		primaryKeyNames.add(review.getUsernameToReview());
		primaryKeyNames.add(review.getUsernameWriter());
		
		try {
			this.store(review, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}
	
	public void loadReview(List <Review> reviewsList, String usernameToReview) {
		
		List <String> primaryKeyValues = new ArrayList <>();
		
		primaryKeyValues.add(usernameToReview);
		
		File folder = new File(this.readDBPath() + Review.class.getSimpleName());
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++) {			
			if(listOfFiles[i].getName().substring(0, usernameToReview.length()).equals(usernameToReview)) {
				
				Review review = new Review();
				primaryKeyValues.add(listOfFiles[i].getName().substring(usernameToReview.length() + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - DAOSerialize.SERIALIZED_EXTENSION.length() - DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length()));
				
				try {
					this.load(review, primaryKeyValues);
				} catch (ElementInDBNotFoundException e) {
					  logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
				} 
				catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
					logger.log(Level.SEVERE, e.toString());
				}
				
				primaryKeyValues.remove(listOfFiles[i].getName().substring(usernameToReview.length() + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - DAOSerialize.SERIALIZED_EXTENSION.length() - DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length()));
				reviewsList.add(review);
			}
		}
		
	}
}
