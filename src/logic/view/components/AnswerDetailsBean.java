package logic.view.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnswerDetailsBean {
	private StringProperty answerer = new SimpleStringProperty(this, "answerer");
	private StringProperty date = new SimpleStringProperty(this, "date");
	
	public AnswerDetailsBean(String answerer, String date) {
		this.setAnswerer(answerer);
		this.setDate(date);
	}
	
	public String getAnswerer() {
		return answerer.getValueSafe();
	}
	public void setAnswerer(String answerer) {
		this.answerer.setValue(answerer);
	}
	public String getDate() {
		return date.getValueSafe();
	}
	public void setDate(String date) {
		this.date.setValue(date);
	}
}
