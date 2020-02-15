package logic.login;

import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.CredentialsBean;
import logic.beans.LogoutBean;
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
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void login(CredentialsBean credentials) throws WrongPasswordException, UserNotFoundException{
    	logger.log(Level.INFO, "Sono qui nel controller e ho passato la bean");

		DAOUser daoRef = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
    	logger.log(Level.INFO, "Sono qui nel controller e ho chiamato la DAO");

		User u = new User();
		daoRef.loadUser(u, credentials.getUsername());
    	logger.log(Level.INFO, "Sono qui nel controller e ho caricato l'utente");
		if (!this.userLoggedList.contains(credentials.getUsername())) {
			if (u.getPassw().equals(credentials.getPassw())) {
				this.userLoggedList.add(u.getUsername());
			}
			else {
				throw new WrongPasswordException();
			}
		}
	}
	
	public void logout(LogoutBean bean) {
		this.userLoggedList.remove(bean.getUsername());
	}
}
