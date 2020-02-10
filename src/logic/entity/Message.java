package logic.entity;

import java.util.Calendar;

public class Message {
	private User user;
	private String text;
	private Object image;
	private Calendar data;

	public Message() {
		this.user = null;
		this.text = "";
		this.image = null;
		this.data = null;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setImage(Object image) {
		this.image = image;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Object getImage() {
		return this.image;
	}
	
	public Calendar getData() {
		return this.data;
	}
}
