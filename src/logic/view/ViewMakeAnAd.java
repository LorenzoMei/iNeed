package logic.view;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.PublishAnAdBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.publishanad.PublishAnAdController;




public class ViewMakeAnAd extends View implements Initializable {
	
	public ViewMakeAnAd() {
		this.setFXMLPath("fxml_makeanad.fxml");
		pBean.setRequestedUsername(View.getProfileName());
	}
	
	 @FXML private TextField typeTextField;
	 @FXML private TextField categoryTextField;
	 @FXML private TextField titleTextField;
	 @FXML private MenuButton typeButton;
	 @FXML private TextArea bodyTextArea;
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionPrinte;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileBean pBean = new ViewProfileBean();
     PublishAnAdBean adBean = new PublishAnAdBean();
     PublishAnAdController controller = PublishAnAdController.getInstance();
     LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean();
     private static String type;

	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationAd, ResourceBundle resourcesAD) {
		 profileName.setText(View.getProfileName());
		 String status = "My location " + locationAd + " my resoursources: " + resourcesAD;
		 logger.log(Level.SEVERE, status);
		}

	 @FXML protected void handleSubmitButtonRequest(ActionEvent event) {
		 type = "Richiesta";
		 typeButton.setText("Request");
	 }
	 
	 @FXML protected void handleSubmitButtonOffer(ActionEvent event) {
		 type = "Offerta";
		 typeButton.setText("Offer");

	 }
	 
	 @FXML protected void handleSubmitButtonSend(ActionEvent event) {
		 actionPrinte.setText("");
	     
	     
	     
	     adBean.setType(type);
	     adBean.setTitle(titleTextField.getText());
	     adBean.setBody(bodyTextArea.getText());
	     adBean.setUsername(View.getProfileName());
	     
	    
			try {
				controller.createAd(adBean);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.log(Level.SEVERE, e.toString() + " Error in ViewMakeAnAd");
			}
		
	     
	     actionPrinte.setText("Ad Posted!");
	 }
	 
	 @FXML protected void handleSubmitButtonRegulations(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go toRegulations");
	     Context.getReference().goNext(GoNextTargets.VIEW_REGULATIONS.getStateName());
	     
	 }
	 
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to MakeAnAd");
    	 Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());	
	 } 
	 
	 @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to ViewFlow");
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());	
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 actionPrinte.setText("");
	 		
		 actionPrinte.setText("ValidateAFavor");
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to User");
	 	Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
		actionPrinte.setText("");
	    actionPrinte.setText("LoggingOut");
		lBean.setUsername(View.getProfileName());
	 	loginController.logout(lBean);
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());
	    }
	 
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionPrinte.setText("");
		     actionPrinte.setText("go to map");
          	 logger.log(Level.INFO, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());
		 }
	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionPrinte.setText("");
	        actionPrinte.setText("Utente");        
	} 
}