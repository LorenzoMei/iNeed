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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;




public class ViewFlow extends View implements Initializable {
	
	public ViewFlow() {
		this.textInputFields = new ArrayList<>();		
		this.setFXMLPath("fxml_flow.fxml");
	}
	
	 String formError = "FORM ERROR!";
	 @FXML private Text actionCancel;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 private final static String GOTOLOGIN = "logic.view.ViewLogin";
	 private final static String GOTOUSER = "logic.view.ViewUser";
	 private final static String GOTOMAP = "logic.view.ViewMap";
	 private final static String GOTOFLOW = "logic.view.ViewFlow";


     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 String status = "My location " + location + " my resoursources: " + resources;
		 logger.log(Level.SEVERE, status);
		 searchTextField.setPromptText("*Es. Meccanico auto");
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

		for(int i = 0; i < textInputFields.size(); i++) {
		        	
		        	logger.log(Level.INFO, textInputFields.get(i).getClass().getSimpleName());
		        	if(textInputFields.get(i).getText().isEmpty()) {
			        	logger.log(Level.INFO, "textfield is : '" + textInputFields.get(i).getText() + "'");
			     		actionCancel.setText("No text Inserted, try again");
			            return;			        	
		
		        	}
		}
 		actionCancel.setText("new search!");
	 }
	 
	 @FXML protected void handleSubmitButtonMakeAnOffer(ActionEvent event) {
	 		actionCancel.setText("MakeAnOffer");

	 }
	 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
	 		actionCancel.setText("MakeARequest");

	 }
	 
	
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
	 		actionCancel.setText("ValidateAFavor");
			
	 }
	
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
	 		Context.getReference().goNext(GOTOUSER);

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
 		Context.getReference().goNext(GOTOFLOW);

 		 actionCancel.setText("");
	     actionCancel.setText("pagina aggiornata");
	 }

	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionCancel.setText("");
	        actionCancel.setText("Utente");
	        
	}
    
}
