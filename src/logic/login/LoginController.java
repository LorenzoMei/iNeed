package logic.login;

import logic.dao.DAOFactory;
import logic.beans.CredentialsBean;
import logic.dao.DAOUser;
import logic.entity.User;

public class LoginController implements LoginControllerInterface{
	
	private static LoginController instance = null;
	
	public static LoginController getInstance() {
		if(instance == null) 
			instance = new LoginController();
		return instance;
	}
	
	public void login(CredentialsBean credentials) {
		
//		TODO implement handling error logic for user not found case
		
		DAOUser daoRef = (DAOUser) DAOFactory.getReference("User").getDAOReference();  
		
		User u = new User();
		daoRef.loadUser(u);
		credentials.setUser(u);
	}
}
