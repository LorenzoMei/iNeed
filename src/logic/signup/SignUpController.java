package logic.signup;

import logic.dao.DAOFactory;
import logic.dao.DAOUser;
import logic.dao.DAOUserJson;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class SignUpController implements SignUpControllerInterface{
	
	private static SignUpController instance;
	
	public static SignUpController getInstance() {
		if(instance == null) 
			instance = new SignUpController();
		return instance;
	}



	public void signUp(SignUpBean bean) throws UsernameAlreadyTakenException {
		
		Boolean checker = true;
		String username = bean.getUsername();
		String passw = bean.getPassword();
		//TODO implement more attributes for the entity User
		String email = bean.getEmail();
		String city = bean.getCity();
		
		User userTemp = new User();
		
		DAOUser DAO = (DAOUser) DAOFactory.getReference("User").getDAOReference();
		
		try {
			DAO.loadUser(userTemp, username);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			userTemp.setUsername(username);
			userTemp.setPassw(passw);
			DAO.storeUser(userTemp);
			
			checker = false;
		}
		
		if(checker) 
			throw new UsernameAlreadyTakenException();

		
		
		
		

	}






}
