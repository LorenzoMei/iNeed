package logic.dao;

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
		primaryKeyNames.add("usernameToReview");
		primaryKeyNames.add("usernameWriter");
		
		try {
			this.store(review, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}
}
