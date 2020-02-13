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
	 @FXML private Text actionCancel;
	 @FXML private GridPane grid;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL location, ResourceBundle resources) {
			logger.log(Level.SEVERE,"My location:  '" + location + "' my resoursources:  '" + resources + "'");
		}
	 
		
	 

	 @FXML protected void handleSubmitButtonMakeAnOffer(ActionEvent event) {
 		 actionCancel.setText("");
	     actionCancel.setText("vado su MakeAnOffer");

	 }
	 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
			
	 }
	 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionCancel.setText("");
	     actionCancel.setText("vado su ViewFlow");
      	 logger.log(Level.SEVERE, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext("logic.view.ViewFlow");
			
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 actionCancel.setText("");
	 		
		 actionCancel.setText("ValidateAFavor");
	    }
	 
	 @FXML protected void handleSubmitButtonGallery(ActionEvent event) {
			
	    }
	 @FXML protected void handleSubmitButtonCurriculum(ActionEvent event) {
			
	    }
	 
	 @FXML protected void handleSubmitButtonFavorites(ActionEvent event) {
			
	    }
	 
	 @FXML protected void handleSubmitButtonRaitings(ActionEvent event) {
			
	    }
	 
	 @FXML protected void handleSubmitButtonWallet(ActionEvent event) {
			
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
 		//Context.getReference().goNext("logic.view.ViewUser");

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewLogin");

	    }
	 
	
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionCancel.setText("");
		     actionCancel.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext("logic.view.ViewMap");

		 }
	 

	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionCancel.setText("");
	        actionCancel.setText("Utente");
	        
	}
    
}

