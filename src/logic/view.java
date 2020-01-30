package logic;

import logic.CheckAnswersOfAnAd.CheckAnswersBean;
import logic.CheckAnswersOfAnAd.CheckAnswersController;
import logic.Login.LoginBean;
import logic.Login.LoginController;
import logic.Login.LoginControllerInterface;
import logic.Login.User;
import logic.PublishAnAd.Post;
import logic.PublishAnAd.PublishARequestAdController;
import logic.PublishAnAd.PublishAnAdBean;
import logic.PublishAnAd.PublishAnAdInterface;
import logic.SignUp.SignUpBean;
import logic.SignUp.SignUpController;
import logic.SignUp.SignUpControllerInterface;
import logic.PublishAnAd.PostFactory;

import java.util.ArrayList;

import logic.CheckAnswersOfAnAd.*;

public class View {
	public static void main(String[] args) {
		//SIGN UP NEW USER
		
		String us = "newUser";
		String pas = "newPassw";
		String email = "djcid@gmail.com";
		String city = "roma";
			
		SignUpBean signUpBean = SignUpBean.getInstance();
		signUpBean.setUsername(us);
		signUpBean.setPassword(pas);
		signUpBean.setEmail(email);
		signUpBean.setCity(city);
				
		SignUpControllerInterface signUpController = SignUpController.getInstance();
		signUpController.signUp();
				
		
		//LOGIN TEST
		
		String username = "newUser";
		String passw = "newPassw";
		
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
		
		//PUBLISH AD TEST
		
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
		
		//CHECK ASNWERS TEST
		
		CheckAnswersBean checkAnswersBean = CheckAnswersBean.getInstance();
		checkAnswersBean.setId(1);
		
		CheckAnswersControllerInterface answersController = CheckAnswersController.getInstance();
		Answers list = answersController.AnswersList();
		ArrayList <String> l = list.getAnswersList();
		
		for(Object obj : l) {
			System.out.println("Candidato: " + (String)obj);
		}
	}
}
