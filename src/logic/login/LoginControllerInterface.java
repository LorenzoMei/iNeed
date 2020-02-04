package logic.login;

import logic.beans.CredentialsBean;
import logic.dao.UserNotFoundException;

public interface LoginControllerInterface {

	public void login(CredentialsBean credentials) throws WrongPasswordException, UserNotFoundException;
}
