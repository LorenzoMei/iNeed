package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class ViewFlowController implements Initializable{

	public ViewFlowController() {
		this.textInputFields = new ArrayList<>();
//		nextViewF = (View) new ViewFlow();
//		pBean.setRequestedUsername(View.getProfileName());
	}
	
	 private View nextViewF;
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionCancelFlow;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 
//	 @FXML private MenuItem profileName;
//	 ViewProfileBean pBean = new ViewProfileBean();
//	 LoginController loginController =  LoginController.getInstance();
//	 LogoutBean lBean = new LogoutBean();

     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL locationflow, ResourceBundle resourcesflow) {
		 String status = "My location " + locationflow + " my resoursources: " + resourcesflow;
		 logger.log(Level.INFO, status);
	     nextViewF = (View) new ViewFlow();
	     logger.log(Level.INFO, "In viewFlow the name of the user is: " + nextViewF.getProfileName());
//		 profileName.setText(View.getProfileName());
		 searchTextField.setPromptText("*Es. Meccanico auto");
		}
	 
	 public TextField getSearchTextField() {
			return searchTextField;
		}
		
	 
	 @FXML protected void handleSubmitButtonSearch(ActionEvent event) {
 		actionCancelFlow.setText("");
 		CheckEmptyField check = new CheckEmptyField();
    	check.populateTextInputFields(this);
    	this.textInputFields = check.getTextInputFields();
    	logger.log(Level.INFO, "Sieze is " + textInputFields.size());

		for(int i = 0; i < textInputFields.size(); i++) {
		        	
		        	logger.log(Level.INFO, textInputFields.get(i).getClass().getSimpleName());
		        	if(textInputFields.get(i).getText().isEmpty()) {
			        	logger.log(Level.INFO, "textfield is : '" + textInputFields.get(i).getText() + "'");
			     		actionCancelFlow.setText("No text Inserted, try again");
			            return;			        	
		
		        	}
		}
 		actionCancelFlow.setText("new search!");
	 }
	  
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
		
//		public final View nextView = (View) new ViewFlow();
		nextViewF = (View) new ViewFlow();
		Context.getReference().getCurrentView().setNextView(nextViewF);
		Context.getReference().goNext();

 		 actionCancelFlow.setText("");
	     actionCancelFlow.setText("pagina aggiornata");
	 }
	 
	 @FXML protected void handleSubmitButtonHelp(ActionEvent event) {
	 		 actionCancelFlow.setText("");
		     actionCancelFlow.setText("Help Page!");
		 }
    
}
