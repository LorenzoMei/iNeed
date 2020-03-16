package logic.beans;

import java.util.Calendar;

public class ValidateAFavorBean {
	
	private String offererUsername;
	private String requesterUsername;
	private Calendar dateOfRequest;
	
	public String getOffererUsername() {
		return this.offererUsername;
	}
	public String getRequesterUsername() {
		return this.requesterUsername;
	}
	public Calendar getDateOfRequest() {
		return this.dateOfRequest;
	}
	
	public void setOffererUsername(String offererUsername){
		this.offererUsername = offererUsername;
	}
	
	public void setRequesterUsername(String requesterUsername) {
		this.requesterUsername = requesterUsername;
	}
	
	public void setDateOfRequest(Calendar dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	
}
