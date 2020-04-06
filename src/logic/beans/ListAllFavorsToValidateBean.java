package logic.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import logic.entity.Favor;
import logic.misc.Order;

public class ListAllFavorsToValidateBean {

	private List<Favor> favorsToValidate;
	private String offererUsername = null;
	private String requesterUsername = null;
	private Calendar dateOfRequest = null;
	private Order order = Order.UNSORTED;
	
	public List<String> getAllOfferersUsername(){
		List <String> allOfferersUsername = new ArrayList<>();
		for (Favor favor : this.favorsToValidate) {
			allOfferersUsername.add(favor.getOffererUsername());
		}
		return allOfferersUsername;
	}
	
	public List<String> getAllRequestersUsername(){
		List <String> allRequestersUsername = new ArrayList<>();
		for (Favor favor : this.favorsToValidate) {
			allRequestersUsername.add(favor.getRequesterUsername());
		}
		return allRequestersUsername;
	}
	
	public List<Calendar> getAllDatesOfRequest(){
		List <Calendar> allDateOfRequest = new ArrayList<>();
		for (Favor favor : this.favorsToValidate) {
			allDateOfRequest.add(favor.getDateOfRequest());
		}
		return allDateOfRequest;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public String getOffererUsername() {
		return this.offererUsername;
	}
	
	public String getRequesterUsername() {
		return this.requesterUsername;
	}
	
	public Calendar getDateOfRequest() {
		return this.dateOfRequest;
	}
	
	public void setDateOfRequest(Calendar dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	
	public void setRequesterUsername (String requesterUsername) {
		this.requesterUsername = requesterUsername;
	}
	
	public void setFavorsToValidate(List<Favor> favorsToValidate) {
		this.favorsToValidate = favorsToValidate;
	}
	
	public void setOffererUsername(String offererUsername) {
		this.offererUsername = offererUsername;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
