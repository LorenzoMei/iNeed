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
//	 @FXML private PasswordField passwordTextField;
	 @FXML private GridPane grid;
     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL location, ResourceBundle resources) {
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
	 		

	 }
	 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
			
	 }
	 
	
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
			
	 }
	
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
	 		Context.getReference().goNext("logic.view.ViewUser");

	 }
		 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewLogin");

	    }
	 
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionCancel.setText("");
		     actionCancel.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked " + getNext());
	    	 Context.getReference().goNext("logic.view.ViewMap");

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
