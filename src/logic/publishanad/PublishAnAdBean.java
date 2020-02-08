package logic.publishanad;

import logic.entity.User;

public class PublishAnAdBean {
	private User user;
	private String title;
	private String body;
	
	public PublishAnAdBean() {
		this.user = null;
		this.title = "";
		this.body = "";
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getBody() {
		return this.body;
	}
}
