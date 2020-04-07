package logic.view.components;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.view.Context;
import logic.view.View;
import logic.view.ViewRegulations;
import logic.viewprofile.ViewProfileController;

public class ViewWalletController implements Initializable{
	
	@FXML private Text textToken;
	
	ViewProfileController viewProfileController =  ViewProfileController.getInstance();
	LoginController loginController =  LoginController.getInstance();
	LogoutBean lBean = new LogoutBean();
	ViewProfileBean pBean = new ViewProfileBean();
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 
		 String status = "My location " + locationUser + " my resoursources: " + resourcesUser;
		 logger.log(Level.INFO, status);
	   
//		 nextViewU = (View) new ViewUser();
		 
		 pBean.setRequestedUsername(Context.getReference().getCurrentView().getProfileName());
		 String nextClassName = "Initializing: " + this.getClass().getSimpleName();
		 logger.log(Level.INFO, nextClassName );
		 
		 viewProfileController.loadProfile(pBean);
		 logger.log(Level.INFO, "user in bean is " + pBean.getName());
		 
		 textToken.setText(Integer.toString(pBean.getToken()));
		}
	
	@FXML protected void handleSubmitButtonRegulations(ActionEvent event) {
	     View nextViewU = (View) new ViewRegulations();
	     nextViewU.setProfileName(Context.getReference().getCurrentView().getProfileName());
		 Context.getReference().getCurrentView().setNextView(nextViewU);
		 Context.getReference().goNext();
	    }
}
