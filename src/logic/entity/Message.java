package logic.entity;

import java.util.Date;

public class Message {
	private String userSenderUsername;
	private String userReceiverUsername;
	private String text;
	private Object image;
	private Date data;

	public Message() {
		this.userSenderUsername = "";
		this.userReceiverUsername = "";
		this.text = "";
		this.image = null;
		this.data = null;
	}
	
	public void setUserSenderUsername(String userSender) {
		this.userSenderUsername = userSender;
	}
	
	public void setUserReceiverUsername(String userReciver) {
		this.userReceiverUsername = userReciver;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setImage(Object image) {
		this.image = image;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getUserSenderUsername() {
		return this.userSenderUsername;
	}
	
	public String getUserReceiverUsername() {
		return this.userReceiverUsername;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Object getImage() {
		return this.image;
	}
	
	public Date getData() {
		return this.data;
	}
}
