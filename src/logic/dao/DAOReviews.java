package logic.dao;

import java.util.List;

import logic.entity.Review;

public interface DAOReviews {
	
	public void storeReview(Review review);
	public void loadReview(List <Review> reviewsList, String usernameToReview);
}
