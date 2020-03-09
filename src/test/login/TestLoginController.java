package test.login;

//import org.junit.Assert;
import org.junit.Test;
import logic.login.*;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
import logic.beans.*;
import logic.dao.UserNotFoundException;

public class TestLoginController {
	
	@Test
	public void testLogin() throws WrongPasswordException, UserNotFoundException, UsernameAlreadyTakenException {
		
		String username = "torkin";
		String passw = "galeone879";
		
		CredentialsBean bean = new CredentialsBean();
		SignUpBean signupBean = new SignUpBean();
		signupBean.setUsername(username);
		signupBean.setPassword(passw);
		try {
			SignUpController.getInstance().signUp(signupBean);
		} catch (UsernameAlreadyTakenException e) {}
		bean.setUsername(username);
		bean.setPassw(passw);
		
		LoginController.getInstance().login(bean);
		
//		Assert.assertEquals(bean.getUser().getUsername(), username);
//		Assert.assertEquals(bean.getUser().getPassw(), passw);
		
	}

}
