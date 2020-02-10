package logic.signup;


import java.util.Calendar;

import logic.dao.DAOFactory;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class SignUpController implements SignUpControllerInterface{
	
	private static SignUpController instance;
	
	public static SignUpController getInstance() {
		if(instance == null) 
			instance = new SignUpController();
		return instance;
	}

	private SignUpController() {}

	public void signUp(SignUpBean bean) throws UsernameAlreadyTakenException {
		
		Boolean checker = true;
		String username = bean.getUsername();
		String passw = bean.getPassword();
		String email = bean.getEmail();
		String city = bean.getCity();
		String name = bean.getSurName();
		String surName = bean.getSurName();
		Calendar bDate = bean.getBirthDate();
		
		User userTemp = new User();
		
		DAOUser DAO = (DAOUser) DAOFactory.getReference("User").getDAOReference();
		
		try {
			DAO.loadUser(userTemp, username);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			userTemp.setUsername(username);
			userTemp.setPassw(passw);
			userTemp.setCity(city);
			userTemp.setEmail(email);
			userTemp.setName(name);
			userTemp.setSurName(surName);
			userTemp.setBDate(bDate);
			DAO.storeUser(userTemp);
			
			checker = false;
		}
		
		if(checker) 
			throw new UsernameAlreadyTakenException();

	}

}
