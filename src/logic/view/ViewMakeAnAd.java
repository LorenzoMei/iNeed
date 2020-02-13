package logic.view;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.entity.User;




public class ViewMakeAnAd extends View implements Initializable {
	
	public ViewMakeAnAd() {
		this.setFXMLPath("fxml_makeanad.fxml");
		activeUser = View.getActiveUser();
	}
	
	 @FXML private TextField typeTextField;
	 @FXML private TextField categoryTextField;
	 @FXML private TextField titleTextField;
	 @FXML private TextArea bodyTextArea;
	 String formError = "FORM ERROR!";
	 @FXML private Text actionPrinte;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 private User activeUser;
	 private final static String GOTOLOGIN = "logic.view.ViewLogin";
	 private final static String GOTOUSER = "logic.view.ViewUser";
	 private final static String GOTOMAP = "logic.view.ViewMap";
	 private final static String GOTOFLOW = "logic.view.ViewFlow";
	 private final static String GOTOMAKEANAD = "logic.view.ViewMakeAnAd";


	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationAd, ResourceBundle resourcesAD) {
		 profileName.setText(activeUser.getUsername());
		 String status = "My location " + locationAd + " my resoursources: " + resourcesAD;
		 logger.log(Level.SEVERE, status);
		}

	 @FXML protected void handleSubmitButtonSend(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("I'm sending");
	 }
	 
	 @FXML protected void handleSubmitButtonRegulations(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("vado su Regulations");	
	 }
	 
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("vado su MakeAnAd");
    	 Context.getReference().goNext(GOTOMAKEANAD);	
	 } 
	 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("vado su ViewFlow");
      	 logger.log(Level.SEVERE, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GOTOFLOW);	
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 actionPrinte.setText("");
	 		
		 actionPrinte.setText("ValidateAFavor");
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("vado su User");
	 	Context.getReference().goNext(GOTOUSER);

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GOTOLOGIN);
	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinte.setText("");
		     actionPrinte.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GOTOMAP);
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinte.setText("");
	        actionPrinte.setText("Utente");        
	} 
}