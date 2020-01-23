package logic;

import logic.Login.LoginBean;
import logic.Login.LoginController;
import logic.Login.LoginControllerInterface;
import logic.Login.User;
import logic.PublishAnAd.Post;
import logic.PublishAnAd.PublishARequestAdController;
import logic.PublishAnAd.PublishAnAdBean;
import logic.PublishAnAd.PublishAnAdInterface;
import logic.PublishAnAd.PostFactory;;

public class View {
	public static void main(String[] args) {
		String username = "user1";
		String passw = "passw1";
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + passw);
		
		LoginBean loginBean = LoginBean.getInstance();
		loginBean.setUsername(username);
		loginBean.setPassw(passw);
		
		LoginControllerInterface control = LoginController.getInstance();
		User found = control.login();
		
		if(found != null)
			System.out.println("Ciao!");
		else
			System.out.println("Errato!");
		
		//PublishAnAdInterface control1 = PublishARequestAdController.getInstance();
		//control1.createPost();
		
		PublishAnAdBean publishAdBean = PublishAnAdBean.getInstance();
		PostFactory p = new PostFactory();
		
		boolean boo = false;
		
		if(boo) {
			String title = "Cerco passaggio per andare a lavoro";
			String body = "Cerco passaggio per andare a lavoro zona anagnina";
			
			publishAdBean.setTitle(title);
			publishAdBean.setBody(body);
			
			PublishAnAdInterface request = p.typePost(1);
			request.createPost();
		}
		else {
			String title = "Offro posto letto";
			String body = "Offro posto letto per le prossime 2 notti. Zona tuscolana";
			
			publishAdBean.setTitle(title);
			publishAdBean.setBody(body);
			
			PublishAnAdInterface offer = p.typePost(2);
			offer.createPost();
		}
	}
}
