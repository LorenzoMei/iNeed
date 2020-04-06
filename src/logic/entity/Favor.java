package logic.entity;

import java.util.Calendar;

public class Favor {
	private int adId ;
	private String adType;
	private String offererUsername;
	private String requesterUsername;
	private Calendar dateOfRequest;
	private Calendar dateOfDue;
	private Calendar dateOfValidation;
	public int getAdId() {
		return adId;
	}
	public String getAdType() {
		return adType;
	}
	public String getOffererUsername() {
		return offererUsername;
	}
	public String getRequesterUsername() {
		return requesterUsername;
	}
	public Calendar getDateOfRequest() {
		return dateOfRequest;
	}
	public Calendar getDateOfDue() {
		return dateOfDue;
	}
	public Calendar getDateOfValidation() {
		return dateOfValidation;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public void setOffererUsername(String offererUsername) {
		this.offererUsername = offererUsername;
	}
	public void setRequesterUsername(String requesterUsername) {
		this.requesterUsername = requesterUsername;
	}
	public void setDateOfRequest(Calendar dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	public void setDateOfDue(Calendar dateOfDue) {
		this.dateOfDue = dateOfDue;
	}
	public void setDateOfValidation(Calendar dateOfValidation) {
		this.dateOfValidation = dateOfValidation;
	}
	
	
}
