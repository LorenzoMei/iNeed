package logic.viewprofile;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.ViewProfileBean;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;
public class ViewProfileController implements ViewProfileControllerInterface{
	
private static ViewProfileController instance;
	
	public static ViewProfileController getInstance() {
		if(instance == null) 
			instance = new ViewProfileController();
		return instance;
	}

	private ViewProfileController() {}
	 Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void loadProfile(ViewProfileBean profile)  {
		
		logger.log(Level.INFO, "requested profile data of username " + profile.getRequestedUsername());
		DAOUser daoRef = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);  
		User user = new User();
		try {
			daoRef.loadUser(user, profile.getRequestedUsername());
		} catch (UserNotFoundException e) {
			logger.log(Level.SEVERE, e.toString() + " Error on loading the profile");
		}
		logger.log(Level.INFO, "setting bean with user field Name " + user.getName());
		profile.setRequesteUser(user);

	}

}


