package logic;

public class LoginBean {
	private String username;
	private String passw;
	private static LoginBean instance;
	
	public static LoginBean getInstance() {
		if(instance == null) 
			instance = new LoginBean();
		return instance;
	}
	
	private LoginBean() {
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
