package logic.publishanad;

import logic.entity.User;

public class PublishAnAdBean {
	private User user;
	private String title;
	private String body;
	private String type;
	
	public PublishAnAdBean() {
		this.user = null;
		this.title = "";
		this.body = "";
		this.type = "";
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
	
	public void setType(String type) {
		this.type = type;
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
	
	public String getType() {
		return this.type;
	}
}
