package logic.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FavorDetailsBean {
	private StringProperty dateOfRequest = new SimpleStringProperty("dateOfRequest");
	private StringProperty offerer = new SimpleStringProperty("offerer");
	
		
	public String getDateOfRequest() {
		return dateOfRequest.getValueSafe();
	}
	public String getOfferer() {
		return offerer.getValueSafe();
	}
	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest.setValue(dateOfRequest);
	}
	public void setOfferer(String offerer) {
		this.offerer.setValue(offerer); ;
	}
	
	
}
