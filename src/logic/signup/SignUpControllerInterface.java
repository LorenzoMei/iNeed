package logic.signup;

import logic.beans.SignUpsBean;

public interface SignUpControllerInterface {

		public void signUp(SignUpsBean bean) throws UsernameAlreadyTakenException;

}
