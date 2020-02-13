package logic.view;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
		this.textInputFields = new ArrayList<>();		
		this.setFXMLPath("fxml_user.fxml");
		activeUser = View.getActiveUser();		
	}
	
	 @FXML private Text nameText;
	 @FXML private Text cityText;
	 @FXML private Text surnameText;
	 @FXML private Text emailText;
	 String formError = "FORM ERROR!";
	 @FXML private Text actionCancel;
	 @FXML private TextField searchTextField;
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
	 
	 public TextField getSearchTextField() {
			return searchTextField;
		}
		
	 
	 @FXML protected void handleSubmitButtonSearch(ActionEvent event) {
 		actionCancel.setText("");
 		CheckEmptyField check = new CheckEmptyField();
    	check.populateTextInputFields(this);
    	this.textInputFields = check.getTextInputFields();
    	logger.log(Level.INFO, "Sieze is " + textInputFields.size());

	
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
		 actionPrinter.setText("");
	     actionPrinter.setText(GOTOUSER);
	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GOTOLOGIN);

	    }
	 
	
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionCancel.setText("");
		     actionCancel.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked " + getNext());
	    	 Context.getReference().goNext(GOTOMAP);

		 }
	 
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewFlow");

 		 actionCancel.setText("");
	     actionCancel.setText("pagina aggiornata");
	 }

	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionCancel.setText("");
	        actionCancel.setText("Utente");
	        
	}
    
}
