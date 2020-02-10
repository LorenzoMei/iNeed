package logic.signup;

public interface SignUpControllerInterface {

		public void signUp(SignUpBean bean) throws UsernameAlreadyTakenException;

}
