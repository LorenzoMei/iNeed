package logic.beans;

public class ActionOnAnswerBean {
	private Boolean confirmed = false;
	private int adId;
	private String adType;
	private String answeredUsername;
	private String answererUsername;
	public Boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getAdId() {
		return adId;
	}
	public String getAdType() {
		return adType;
	}
	public String getAnsweredUsername() {
		return answeredUsername;
	}
	public String getAnswererUsername() {
		return answererUsername;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public void setAnsweredUsername(String answeredUsername) {
		this.answeredUsername = answeredUsername;
	}
	public void setAnswererUsername(String answererUsername) {
		this.answererUsername = answererUsername;
	}
}
