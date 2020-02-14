package logic.beans;

public class CredentialsBean {
	private String username;
	private String passw;
	
	public CredentialsBean() {
		this.username = "";
		this.passw = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassw() {
		return this.passw;
	}
}
