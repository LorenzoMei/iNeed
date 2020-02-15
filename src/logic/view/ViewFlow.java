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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;

public class ViewFlow extends View implements Initializable {
	
	public ViewFlow() {
		this.textInputFields = new ArrayList<>();		
		this.setFXMLPath("fxml_flow.fxml");	
		pBean.setRequestedUsername(View.getProfileName());
	}
	
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionCancel;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileBean pBean = new ViewProfileBean();
	 LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean();

     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL locationflow, ResourceBundle resourcesflow) {
		 String status = "My location " + locationflow + " my resoursources: " + resourcesflow;
		 logger.log(Level.INFO, status);
		 profileName.setText(View.getProfileName());
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
	 
	 
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
	 		actionCancel.setText("");
	 		actionCancel.setText("MakeAnAd");
		 	Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());


	 }
	 
	
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
	 		actionCancel.setText("");

	 		actionCancel.setText("ValidateAFavor");
			
	 }
	
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
	 		actionCancel.setText("");
	 		Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	 }
		 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
	 		actionCancel.setText("LoggingOut");
	 		lBean.setUsername(View.getProfileName());
	 		loginController.logout(lBean);
	 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionCancel.setText("");
		     actionCancel.setText("vado su map");
          	 logger.log(Level.INFO, "Print this when viewMap is clicked " + getNext());
	    	 Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());

		 }
	 
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());

 		 actionCancel.setText("");
	     actionCancel.setText("pagina aggiornata");
	 }

	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionCancel.setText("");
	        actionCancel.setText("Utente");
	        
	}
    
}
