package logic.signup;

import logic.beans.SignUpBean;

public interface SignUpControllerInterface {

		public void signUp(SignUpBean bean) throws UsernameAlreadyTakenException;

}
