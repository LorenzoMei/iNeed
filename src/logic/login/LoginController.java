package logic.login;

public class LoginController implements LoginControllerInterface{
	private static LoginController instance;
	LoginBean bean = LoginBean.getInstance();
	
	public static LoginController getInstance() {
		if(instance == null) 
			instance = new LoginController();
		return instance;
	}
	
	private LoginController() {
	}
	
	public User login() {
		String username = bean.getUsername();
		String passw = bean.getPassw();
		User u = UserDao.findUsernameAndPassw(username, passw);
		return u;
	}
}
