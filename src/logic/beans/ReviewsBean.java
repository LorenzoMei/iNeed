package logic.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.entity.Review;

public class ReviewsBean {
	
	private Map<KeyReviewsBean, Object> attributes = new HashMap<>();
	
	public void setUsernameWriter(String usernameWriter) {
		attributes.put(KeyReviewsBean.REVIEWSBEAN_USERNAMEWRITER, usernameWriter);
	}
	
	public void setUsernameToReview(String usernameToReview) {
		attributes.put(KeyReviewsBean.REVIEWSBEAN_USERNAMETOREVIEW, usernameToReview);
	}
	
	public void setVote(int vote) {
		attributes.put(KeyReviewsBean.REVIEWSBEAN_VOTE, vote);
	}
	
	public void setText(String text) {
		attributes.put(KeyReviewsBean.REVIEWSBEAN_TEXT, text);
	}
	
	public void setReviewsList(List <Review> reviewsList) {
		attributes.put(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST, reviewsList);
	}
	
	public List<String> getAllUsernameWriter(){
		List<String> result = new ArrayList<>();
		for (int i = 0; i < ((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).size(); i ++) {
			result.add(((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).get(i).getUsernameWriter());
		}
		return result;
	}
	
	public List<String> getAllText(){
		List<String> result = new ArrayList<>();
		for (int i = 0; i < ((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).size(); i ++) {
			result.add(((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).get(i).getText());
		}
		return result;
	}
	
	public List<Integer> getAllVote(){
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < ((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).size(); i ++) {
			result.add(((List <Review>) this.getAttributes().get(KeyReviewsBean.REVIEWSBEAN_REVIEWSLIST)).get(i).getVote());
		}
		return result;
	}
	
	public Map<KeyReviewsBean, Object> getAttributes(){
		return this.attributes;
	}
	
}
