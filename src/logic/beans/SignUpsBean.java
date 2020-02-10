package logic.beans;

import java.util.Calendar;


public class SignUpsBean {
	
	private static SignUpsBean instance;
	
	private String username;
	private String passw;
	private String email;
	private String city;
	private String name;
	private String surName;
	private Calendar bDate;
	
	public static SignUpsBean getInstance() {
		if(instance == null)
			instance = new SignUpsBean();
		return instance;
	}
	
	public SignUpsBean() {
		this.username = "";
		this.passw = "";
		this.email = "";
		this.city = "";
		this.name = "";
		this.surName = "";
		this.bDate = null;
	}
	
	//all the sets on attributes
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String passw) {
		this.passw = passw;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public void setBirthDate(Calendar userBirthDate) {
		this.bDate = userBirthDate;
	}
	
	//all the gets on attributes

	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.passw;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getSurName() {
		return this.surName;
	}
	
	public String getname() {
		return this.name;
	}
	
	public Calendar getBirthDate() {
		return this.bDate;
	}
	
}