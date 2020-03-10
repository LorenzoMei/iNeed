package logic.entity;

import java.util.Calendar;

public class Favor {
	private Ad ad;
	private User offerer;
	private User requester;
	private Calendar dateOfRequest;
	private Calendar dateOfDue;
	private Calendar dateOfValidation;
	
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	public void setOfferer(User offerer) {
		this.offerer = offerer;
	}
	public void setRequester(User requester) {
		this.requester = requester;
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
	
	public Ad getAd() {
		return this.ad;
	}
	public User getOfferer() {
		return this.offerer;
	}
	public User getRequester() {
		return this.requester;
	}
	public Calendar getDateOfRequest() {
		return this.dateOfRequest;
	}
	public Calendar getDateOfDue() {
		return this.dateOfDue;
	}
	public Calendar getDateOfValidation() {
		return this.dateOfValidation;
	}
}
