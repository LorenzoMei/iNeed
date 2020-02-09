package logic.entity;

import java.util.Calendar;

public class User {
	
	private String username;
	private String passw;
	private String city;
	private String email;
	private String name;
	private String surName;
	private User friend;
	private Calendar bDate;

	
	public User() {
		this.username = "";
		this.passw = "";
		this.city = "";
		this.email = "";
		this.name = "";
		this.surName = "";
		this.bDate = null;
	}
	
	//all the sets on attributes
	
	public void setBDate(Calendar val) {
		this.bDate = val;		
	}
	
	public void setFriend(User val) {
		this.friend = val;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	//all the gets on attributes
	public User getFriend() {
		return this.friend;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassw() {
		return this.passw;
	}
	public String getSurName() {
		return this.surName;
	}
	
	public String getname() {
		return this.name;
	}
	
	public Calendar getBDate() {
		return this.bDate;
	}
	
}