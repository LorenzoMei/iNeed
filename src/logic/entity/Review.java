package logic.entity;

public class Review {
	private String usernameWriter;
	private String usernameToReview;
	private int vote;
	private String text;
	
	public void setUsernameWriter(String usernameWriter) {
		this.usernameWriter = usernameWriter;
	}
	
	public void setUsernameToReview(String usernameToReview) {
		this.usernameToReview = usernameToReview;
	}
	
	public void setVote(int vote) {
		this.vote = vote;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getUsernameWriter() {
		return this.usernameWriter;
	}
	
	public String getUsernameToReview() {
		return this.usernameToReview;
	}
	
	public int getVote() {
		return this.vote;
	}
	
	public String getText() {
		return this.text;
	}
	
}
