package logic.entity;

import java.util.Calendar;

public class Answer {
	private int id;
	private String username;
	private String type;
	private Calendar date;
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getType() {
		return this.type;
	}
}
