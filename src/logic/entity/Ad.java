package logic.entity;

import java.util.Calendar;

public abstract class Ad {
	protected int id;
	protected String ownerUsername = "";
	protected String title = "";
	protected String body = "";
	protected Calendar data = null;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setOwnerUsername(String user) {
		this.ownerUsername = user;
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
	
	public String getOwnerUsername() {
		return this.ownerUsername;
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
