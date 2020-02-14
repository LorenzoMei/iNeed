package logic.login;

import logic.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;

import logic.beans.CredentialsBean;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class LoginController {
	
	private static LoginController instance = null;
	private List<String> userLoggedList = new ArrayList<>();
	
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
		if (!this.userLoggedList.contains(credentials.getUsername())) {
			if (u.getPassw().equals(credentials.getPassw())) {
				this.userLoggedList.add(u.getUsername());
			}
			else {
				throw new WrongPasswordException();
			}
		}
	}
}
