package logic.contactuser;

public class ContactUserBean {
	private String userSenderUsername;
	private String userReciverUsername;
	private String text;
	private Object image;
	
	public ContactUserBean() {
		this.userSenderUsername = null;
		this.userReciverUsername = null;
		this.text = "";
		this.image = null;
	}
	
	public void setUserSenderUsername(String userSender) {
		this.userSenderUsername = userSender;
	}
	
	public void setUserReciverUsername(String userReciver) {
		this.userReciverUsername = userReciver;
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
	
	public String getUserReciverUsername() {
		return this.userReciverUsername;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Object getImage() {
		return this.image;
	}
}
