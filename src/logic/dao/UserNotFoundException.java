package logic.dao;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670250520292908176L;
	private String username;
	
	public UserNotFoundException (String username) {
		super("user with username " + username + "not found");
		this.username = username;
		
	}
	
	public String getUsername() {
		return this.username;
	}

}
