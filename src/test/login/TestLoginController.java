package test.login;

import org.junit.Assert;
import org.junit.Test;
import logic.login.*;
import logic.beans.*;
import logic.dao.UserNotFoundException;

public class TestLoginController {
	
	@Test
	public void testLogin() throws WrongPasswordException, UserNotFoundException {
		
		String username = "Daniele";
		String passw = "galeone879";
		
		CredentialsBean bean = new CredentialsBean();
		bean.setUsername(username);
		bean.setPassw(passw);
		
		LoginController.getInstance().login(bean);
		
		Assert.assertEquals(bean.getUser().getUsername(), username);
		Assert.assertEquals(bean.getUser().getPassw(), passw);
		
	}

}
