package logic.beans;

import java.util.HashMap;
import java.util.Map;

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
	
	public Map<KeyReviewsBean, Object> getAttributes(){
		return this.attributes;
	}
	
}
