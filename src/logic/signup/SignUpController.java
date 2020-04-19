package logic.signup;


import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.SignUpBean;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class SignUpController{
	
	private static SignUpController instance;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static SignUpController getInstance() {
		if(instance == null) 
			instance = new SignUpController();
		return instance;
	}

	private SignUpController() {}

	public void signUp(SignUpBean bean) throws UsernameAlreadyTakenException {
		
		String username = bean.getUsername();
		String passw = bean.getPassword();
		String email = bean.getEmail();
		String city = bean.getCity();
		String name = bean.getName();
		String surName = bean.getSurName();
		Calendar bDate = bean.getBirthDate();
		
		User userTemp = new User();
		
		DAOUser dao = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		
		try {
			dao.loadUser(userTemp, username);
			throw new UsernameAlreadyTakenException();
		} catch (UserNotFoundException e) {
			logger.log(Level.WARNING, e.toString());
			userTemp.setUsername(username);
			userTemp.setPassw(passw);
			userTemp.setCity(city);
			userTemp.setEmail(email);
			userTemp.setName(name);
			userTemp.setSurname(surName);
			userTemp.setBDate(bDate);
			dao.storeUser(userTemp);		
			return;
		}
		

	}

}
