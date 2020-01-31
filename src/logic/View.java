package logic;

import logic.login.LoginController;
import logic.login.LoginControllerInterface;
import logic.publishanad.Post;
import logic.publishanad.PostFactory;
import logic.publishanad.PublishARequestAdController;
import logic.publishanad.PublishAnAdBean;
import logic.publishanad.PublishAnAdInterface;
import logic.signup.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.SignUpControllerInterface;

import java.util.ArrayList;

import logic.beans.CredentialsBean;
import logic.checkanswersofanad.*;
import logic.entity.User;

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
		
		CredentialsBean loginBean = CredentialsBean.getInstance();
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
