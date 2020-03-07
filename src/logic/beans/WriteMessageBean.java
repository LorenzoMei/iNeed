package logic.beans;

public class WriteMessageBean {
	
	private String userSenderUsername;
	private String userReceiverUsername;
	private String text;
	private Object image;
	
	public WriteMessageBean() {
		this.userSenderUsername = null;
		this.userReceiverUsername = null;
		this.text = "";
		this.image = null;
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
}
