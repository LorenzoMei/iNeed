package logic.beans;

import logic.entity.User;

public class CredentialsBean {
	private String username;
	private String passw;
	private User user;
	
	public CredentialsBean() {
		this.username = "";
		this.passw = "";
		this.user = null;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public void setUser(User ref) {
		this.user = ref;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassw() {
		return this.passw;
	}
	
	public User getUser() {
		return this.user;
	}
}
