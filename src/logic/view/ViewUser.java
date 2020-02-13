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
import logic.entity.User;




public class ViewUser extends View implements Initializable {
	
	public ViewUser() {
		this.setFXMLPath("fxml_user.fxml");
		activeUser = View.getActiveUser();		
	}
	
	 @FXML private Text nameText;
	 @FXML private Text cityText;
	 @FXML private Text surnameText;
	 @FXML private Text emailText;
	 String formError = "FORM ERROR!";
	 @FXML private Text actionPrinter;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 private User activeUser;
	 private final static String GOTOLOGIN = "logic.view.ViewLogin";
	 private final static String GOTOUSER = "logic.view.ViewUser";
	 private final static String GOTOMAP = "logic.view.ViewMap";
	 private final static String GOTOFLOW = "logic.view.ViewFlow";
	 private final static String GOTOMAKEANAD = "logic.view.ViewMakeAnAd";
	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 nameText.setText(activeUser.getname());
		 cityText.setText(activeUser.getCity());
		 surnameText.setText(activeUser.getSurName());
		 emailText.setText(activeUser.getEmail());
		 profileName.setText(activeUser.getUsername());
		 String status = "My location " + locationUser + " my resoursources: " + resourcesUser;
		 logger.log(Level.SEVERE, status);
		}

	
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su MakeAnAd");
	 	Context.getReference().goNext(GOTOMAKEANAD);

			
	 } 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su ViewFlow");
      	 logger.log(Level.SEVERE, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GOTOFLOW);	
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
	     actionPrinter.setText(GOTOUSER);
	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GOTOLOGIN);
	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinter.setText("");
		     actionPrinter.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GOTOMAP);
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinter.setText("");
	        actionPrinter.setText("Utente");        
	} 
}