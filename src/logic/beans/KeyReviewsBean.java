package logic.beans;

public enum KeyReviewsBean {
	REVIEWSBEAN_USERNAMEWRITER("usernameWriter"),
	REVIEWSBEAN_USERNAMETOREVIEW("usernameToReview"),
	REVIEWSBEAN_VOTE("vote"),
	REVIEWSBEAN_TEXT("text");
	
	private final String keyName;
	
	private KeyReviewsBean(String keyName) {
		this.keyName = keyName;
	}
	
	public String getKeyName() {
		return this.keyName;
	}
}
