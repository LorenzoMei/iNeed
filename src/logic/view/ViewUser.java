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
import logic.beans.ViewProfileBean;
import logic.viewprofile.ViewProfileController;





public class ViewUser extends View implements Initializable {
	
	public ViewUser() {
		this.setFXMLPath("fxml_user.fxml");
		pBean.setRequestedUsername(View.getProfileName());
		viewProfileController.loadProfile(pBean);
	}
	
	 @FXML private Text nameText;
	 @FXML private Text cityText;
	 @FXML private Text surnameText;
	 @FXML private Text emailText;
	 @FXML private Text actionPrinter;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileBean pBean = new ViewProfileBean();
	 ViewProfileController viewProfileController = ViewProfileController.getInstance();
	 

	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 
		 String name = pBean.getName();
		 String city = pBean.getCity();
		 String surname = pBean.getSurName();
		 String email = pBean.getEmail();
		 
		 nameText.setText(name);
		 cityText.setText(city);
		 surnameText.setText(surname);
		 emailText.setText(email);
		 profileName.setText(View.getProfileName());
		 String status = "My location " + locationUser + " my resoursources: " + resourcesUser;
		 logger.log(Level.INFO, status);
		}

	
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su MakeAnAd");
	 	Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());

			
	 } 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su ViewFlow");
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());	
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 actionPrinter.setText("");
		 actionPrinter.setText("ValidateAFavor");
	    }
	 @FXML protected void handleSubmitButtonGallery(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Gallery");			
	    }
	 @FXML protected void handleSubmitButtonCurriculum(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Curriculum");	
	    }
	 
	 @FXML protected void handleSubmitButtonFavorites(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Favorites");
	    }
	 
	 @FXML protected void handleSubmitButtonRaitings(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Raitings");
	    }
	 
	 @FXML protected void handleSubmitButtonWallet(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Wallet");
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText(GoNextTargets.VIEW_USER.getStateName());
	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());
	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinter.setText("");
		     actionPrinter.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinter.setText("");
	        actionPrinter.setText("Utente");        
	} 
}