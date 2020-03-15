package logic.reviews;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.KeyReviewsBean;
import logic.beans.ReviewsBean;
import logic.dao.DAOFactory;
import logic.dao.DAOReviews;
import logic.dao.DAOSupportedEntities;
import logic.entity.Review;
import logic.misc.NoSuchSetterException;
import logic.misc.ReflectionMiscellaneous;

public class ReviewsController {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	private static ReviewsController instance = null; 
	
	public static ReviewsController getInstance() {
		if(instance == null) 
			instance = new ReviewsController();
		return instance;
	}
	
	private ReviewsController() {}
	
	public void writeReview(ReviewsBean reviewsBean) {
		
		Review review = new Review();
		
		EnumSet.allOf(KeyReviewsBean.class).forEach(key ->{
			try {
				ReflectionMiscellaneous.getSetter(key.getKeyName(), review).invoke(review, reviewsBean.getAttributes().get((Object) key));
			} catch (NoSuchSetterException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.log(Level.SEVERE, e.toString());
			}
		});
		
		DAOReviews dao = (DAOReviews) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.REVIEW);
		dao.storeReview(review);
	}
	
	
}
