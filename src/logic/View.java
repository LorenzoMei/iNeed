package logic;

import logic.PublishARequestAd.Post;
import logic.PublishARequestAd.PublishARequestAdBean;
import logic.PublishARequestAd.PublishARequestAdController;
import logic.PublishARequestAd.PublishARequestAdInterface;
import logic.login.LoginBean;
import logic.login.LoginController;
import logic.login.LoginControllerInterface;
import logic.login.User;

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
		
		String title = "Cerco passaggio per andare a lavoro";
		String body = "Cerco passaggio per andare a lavoro zona anagnina";
		
		PublishARequestAdBean b1 = PublishARequestAdBean.getInstance();
		b1.setTitle(title);
		b1.setBody(body);
		
		PublishARequestAdInterface control1 = PublishARequestAdController.getInstance();
		control1.createPost();
	}
}
