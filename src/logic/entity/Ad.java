package logic.entity;

import java.util.Calendar;

public abstract class Ad {
	protected int id;
	protected String username = "";
	protected String title = "";
	protected String body = "";
	protected Calendar data = null;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String user) {
		this.username = user;
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
	
	public String getUsername() {
		return this.username;
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
