package logic.login;

public class LoginController implements LoginControllerInterface{
	
	private static LoginController instance = null;
	
	public static LoginController getInstance() {
		if(instance == null) 
			instance = new LoginController();
		return instance;
	}
	
	public void login(CredentialsBean credentials) {
		String username = credentials.getUsername();
		String passw = credentials.getPassw();
		User u = UserDao.findUsernameAndPassw(username, passw);
		credentials.setUser(u);
	}
}
