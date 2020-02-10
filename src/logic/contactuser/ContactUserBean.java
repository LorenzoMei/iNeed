package logic.contactuser;

import logic.entity.User;

public class ContactUserBean {
	private User user;
	private String text;
	private Object image;
	
	public ContactUserBean() {
		this.user = null;
		this.text = "";
		this.image = null;
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
	
	public User getUser() {
		return this.user;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Object getImage() {
		return this.image;
	}
}
