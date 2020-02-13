package logic.view;


import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;




public class ViewUser extends View implements Initializable {
	
	public ViewUser() {
		this.setFXMLPath("fxml_user.fxml");
	}
	
	 String formError = "FORM ERROR!";
	 @FXML private Text actionPrinter;
	 @FXML private GridPane grid;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 String status = "My location " + location + " my resoursources: " + resources;
		 logger.log(Level.SEVERE, status);
		}

	 @FXML protected void handleSubmitButtonMakeAnOffer(ActionEvent event) {
 		 actionPrinter.setText("");
	     actionPrinter.setText("vado su MakeAnOffer");

	 } 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su MakeARequest");
			
	 } 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su ViewFlow");
      	 logger.log(Level.SEVERE, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext("logic.view.ViewFlow");	
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
	     actionPrinter.setText("vado su User");
	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewLogin");
	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinter.setText("");
		     actionPrinter.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext("logic.view.ViewMap");
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinter.setText("");
	        actionPrinter.setText("Utente");        
	} 
}