package logic.login;

import logic.dao.DAOFactory;
import logic.beans.CredentialsBean;
import logic.dao.DAO;
import logic.entity.User;

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
		
		DAO daoRef = DAOFactory.getReference().getDAOReference();  
		
		User u = daoRef.loadUser(username, passw);
		credentials.setUser(u);
	}
}
