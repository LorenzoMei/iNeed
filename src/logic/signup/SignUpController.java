package logic.signup;

import logic.dao.DAOUserJson;

public class SignUpController implements SignUpControllerInterface{
	
	private static SignUpController instance;
	
	public static SignUpController getInstance() {
		if(instance == null) 
			instance = new SignUpController();
		return instance;
	}

	private SignUpController(){	
	}

	public void signUp() {
		SignUpBean bean = SignUpBean.getInstance();
		String username = bean.getUsername();
		String passw = bean.getPassword();
		String email = bean.getEmail();
		String city = bean.getCity();
		
		DAOUserJson.createAccount(username, passw, email, city);
	}
}
