package logic.entity;

import java.util.Calendar;

public abstract class Ad {
	protected int id;
	protected String ownerUsername = "";
	protected String title = "";
	protected String body = "";
	protected String category = "";
	protected Calendar data = null;
	
	public void setId(int id) {
		this.id = id;
	}
	
	//all the sets
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
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	//all the gets
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
	
	public String getCategory() {
		return this.category;
	}
}
