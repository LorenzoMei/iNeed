package logic.login;

import logic.dao.DAOFactory;
import logic.beans.CredentialsBean;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class LoginController implements LoginControllerInterface{
	
	private static LoginController instance = null;
	
	public static LoginController getInstance() {
		if(instance == null) 
			instance = new LoginController();
		return instance;
	}
	
	private LoginController() {}
	
	public void login(CredentialsBean credentials) throws WrongPasswordException, UserNotFoundException{
		
		
		DAOUser daoRef = (DAOUser) DAOFactory.getReference().getDAOReference("User");  
		User u = new User();
		daoRef.loadUser(u, credentials.getUsername());
		if (u.getPassw().equals(credentials.getPassw())) {
			credentials.setUser(u);
		}
		else {
			throw new WrongPasswordException();
		}
	}
}
