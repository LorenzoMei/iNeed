package logic;

public class View {
	public static void main(String[] args) {
		String username = "user1";
		String passw = "passw1";
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + passw);
		
		LoginBean b = LoginBean.getInstance();
		b.setUsername(username);
		b.setPassw(passw);
		
		LoginControllerInterface control = LoginController.getInstance();
		User found = control.login();
		
		if(found != null)
			System.out.println("Ciao!");
		else
			System.out.println("Errato!");
	}
}
