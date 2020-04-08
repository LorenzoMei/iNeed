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
	
	public int getNumOfFavors() {
		return this.favorsToValidate.size();
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public String getQueriedOffererUsername() {
		return this.offererUsername;
	}
	
	public String getQueriedRequesterUsername() {
		return this.requesterUsername;
	}
	
	public Calendar getQueriedDateOfRequest() {
		return this.dateOfRequest;
	}
	
	public void setQueriedDateOfRequest(Calendar dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	
	public void setQueriedRequesterUsername (String requesterUsername) {
		this.requesterUsername = requesterUsername;
	}
	
	public void setQueriedFavorsToValidate(List<Favor> favorsToValidate) {
		this.favorsToValidate = favorsToValidate;
	}
	
	public void setQueriedOffererUsername(String offererUsername) {
		this.offererUsername = offererUsername;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String getOffererUsername(int i) {
		return this.favorsToValidate.get(i).getOffererUsername();
	}
	public String getRequesterUsername(int i) {
		return this.favorsToValidate.get(i).getRequesterUsername();
	}
	public Calendar getDateOfRequest(int i) {
		return this.favorsToValidate.get(i).getDateOfRequest();
	}
	
}
