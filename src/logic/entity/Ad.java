package logic.entity;

import java.util.Calendar;

public abstract class Ad {
	protected int id;
	protected User user = null;
	protected String title = "";
	protected String body = "";
	protected Calendar data = null;
	
	public void setId(int id) {
		this.id = id;
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
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public int getId() {
		return id;
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
	
	public Calendar getData() {
		return this.data;
	}
}
