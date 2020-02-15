package logic.view;


import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.viewprofile.ViewProfileController;


public class ViewUser extends View implements Initializable {
	
	public ViewUser() {
		this.setFXMLPath("fxml_user.fxml");
	}
	
	 @FXML private Text nameText;
	 @FXML private Text cityText;
	 @FXML private Text surnameText;
	 @FXML private Text emailText;
	 @FXML private Text actionPrinter;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileBean pBean = new ViewProfileBean();
	 ViewProfileController viewProfileController =  ViewProfileController.getInstance();
	 LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean();
	 	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 
		 
		 pBean.setRequestedUsername(View.getProfileName());
		 logger.log(Level.INFO, "initializing " + this.getClass().getSimpleName());
		 
		 viewProfileController.loadProfile(pBean);
		 logger.log(Level.INFO, "user in bean is " + pBean.getName());
		 String name = pBean.getName();
		 String city = pBean.getCity();
		 String surname = pBean.getSurName();
		 String email = pBean.getEmail();
		 
		 nameText.setText(name);
		 cityText.setText(city);
		 surnameText.setText(surname);
		 emailText.setText(email);
		 profileName.setText(View.getProfileName());
		}

	
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go toMakeAnAd");
	 	Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());

			
	 } 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to ViewFlow");
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());	
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 actionPrinter.setText("");
		 actionPrinter.setText("ValidateAFavor");
	    }
	 @FXML protected void handleSubmitButtonGallery(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Gallery");			
	    }
	 @FXML protected void handleSubmitButtonCurriculum(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Curriculum");
	     Context.getReference().goNext(GoNextTargets.VIEW_CURRICULUMMAIN.getStateName());
	    }
	 
	 @FXML protected void handleSubmitButtonFavorites(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Favorites");
	    }
	 
	 @FXML protected void handleSubmitButtonRaitings(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Raitings");
	    }
	 
	 @FXML protected void handleSubmitButtonWallet(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Wallet");
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText(GoNextTargets.VIEW_USER.getStateName());
	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
		lBean.setUsername(View.getProfileName());
	 	loginController.logout(lBean);
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());
	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinter.setText("");
		     actionPrinter.setText("go to map");
          	 logger.log(Level.INFO, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinter.setText("");
	        actionPrinter.setText("Utente");        
	} 
}