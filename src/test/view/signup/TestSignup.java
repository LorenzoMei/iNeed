package test.view.signup;

import org.junit.Test;
import logic.view.signup.*;
import javafx.application.*;

public class TestSignup {
	
	@Test
	public void testView() throws ClassNotFoundException {
		Application.launch(Class.forName("logic.view.signup.ViewSignUp").asSubclass(Application.class), (String[])null);
	}
	
}
