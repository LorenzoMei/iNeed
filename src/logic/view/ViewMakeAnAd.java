package logic.view;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
	 @FXML private TextField othersTextField;
	 @FXML private TextField titleTextField;
	 @FXML private MenuButton typeButton;
	 @FXML private MenuButton categoryButton;
	 @FXML private TextArea bodyTextArea;
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionPrinte;
	 @FXML private AnchorPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileBean pBean = new ViewProfileBean();
     PublishAnAdBean adBean = new PublishAnAdBean();
     PublishAnAdController controller = PublishAnAdController.getInstance();
     LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean();
     private static String type;
     private static String category;
     private static boolean other = false;

	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationAd, ResourceBundle resourcesAD) {
		 titleTextField.setPromptText("*Es. I need a bed this weekend...");
		 profileName.setText(View.getProfileName());
		 othersTextField.setEditable(false);
		 String status = "My location " + locationAd + " my resoursources: " + resourcesAD;
		 logger.log(Level.SEVERE, status);
		}
	 
	 
	 public static void setType(String type) {
	 		ViewMakeAnAd.type = type;

	 	}
	 
	 
	     public static String getType() {
	    	 return ViewMakeAnAd.type;
	     }
	     
	     public static void setOther(boolean other) {
		  		ViewMakeAnAd.other = other;

		  	}
	     
	     public static boolean getOther() {
	     	 return ViewMakeAnAd.other;
	     }
	     
	     
	     public static void setCategory(String category) {
	  		ViewMakeAnAd.category = category;

	  	}
	     
	     public static String getCategory() {
	     	 return ViewMakeAnAd.category;
	    }
	 

	 @FXML protected void handleSubmitButtonRequest(ActionEvent event) {
		 ViewMakeAnAd.setType("Richiesta");
		 typeButton.setText("Request");
	 }
	 
	 @FXML protected void handleSubmitButtonOffer(ActionEvent event) {
		 ViewMakeAnAd.setType("Offerta");
		 typeButton.setText("Offer");

	 }
	 
	 @FXML protected void handleSubmitButtonElectronics(ActionEvent event) {
		 ViewMakeAnAd.setCategory("Electronics");
		 categoryButton.setText("Electronics");
	 }
	 
	 @FXML protected void handleSubmitButtonHydraulic(ActionEvent event) {
		 ViewMakeAnAd.setCategory("Hydraulic");
		 categoryButton.setText("Hydraulic");
	 }
	 
	 @FXML protected void handleSubmitButtonGardering(ActionEvent event) {
		 ViewMakeAnAd.setCategory("Gardering");
		 categoryButton.setText("Gardering");
	 }
	 
	 @FXML protected void handleSubmitButtonInformatic(ActionEvent event) {
		 ViewMakeAnAd.setCategory("Informatic");
		 categoryButton.setText("Informatic");
	 }
	 
	 @FXML protected void handleSubmitButtonBed(ActionEvent event) {
		 ViewMakeAnAd.setCategory("Bed sharing");
		 categoryButton.setText("Bed sharing");

	 }
	 
	 @FXML protected void handleSubmitButtonOther(ActionEvent event) {
		 categoryButton.setText("Other");
		 othersTextField.setEditable(true);
		  ViewMakeAnAd.setOther(true);
	 }
	 
	 
	 @FXML protected void handleSubmitButtonSend(ActionEvent event) {
		 actionPrinte.setText("");
		
		 logger.log(Level.INFO,  "il titolo e': " + ViewMakeAnAd.getType() );
		 
		 if(ViewMakeAnAd.getType() == null || titleTextField.getText().isEmpty()) {
				logger.log(Level.INFO,  "Eh si sono proprio un null " );
	     		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "insert a type please!");
	            return;
	     }

		 else if(titleTextField.getText().isEmpty()) {
     		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Insert a title please!");
            return;
     	}
		
	     
		 else {
			 if(!other) {
			     adBean.setCategory(ViewMakeAnAd.getCategory());   	 
		     }
		     
		     else {
		    	 adBean.setCategory(othersTextField.getText());
		     }
			 
				logger.log(Level.INFO,  "il titolo e': " + ViewMakeAnAd.getType() );
	
		     
		     adBean.setType(ViewMakeAnAd.getType());
		     adBean.setTitle(titleTextField.getText());
		     adBean.setBody(bodyTextArea.getText());
		     adBean.setOwnerUsername(View.getProfileName());
		     
		    
				try {
					controller.createAd(adBean);
				} catch (IllegalAccessException | InvocationTargetException e) {
					logger.log(Level.SEVERE, e.toString() + " Error in ViewMakeAnAd");
				}
		 }
		
	     
	     actionPrinte.setText("Ad Posted!");
	 }
	 
	 @FXML protected void handleSubmitButtonRegulations(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go toRegulations");
	     Context.getReference().goNext(GoNextTargets.VIEW_REGULATIONS.getStateName());
	     
	 }
	 
	 @FXML protected void handleSubmitButtonMakeAnAdAd(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to MakeAnAd");
    	 Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());	
	 } 
	 
	 @FXML protected void handleSubmitButtonViewFlowAd(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to ViewFlow");
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
    	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());	
	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavorAd(ActionEvent event) {
		 actionPrinte.setText("");
	 		
		 actionPrinte.setText("ValidateAFavor");
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfileAd(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go to User");
	 	Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonExitAd(ActionEvent event) {
		actionPrinte.setText("");
	    actionPrinte.setText("LoggingOut");
		lBean.setUsername(View.getProfileName());
	 	loginController.logout(lBean);
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());
	    }
	 
	 @FXML protected void handleSubmitButtonViewMapAd(ActionEvent event) {
	 		 actionPrinte.setText("");
		     actionPrinte.setText("go to map");
          	 logger.log(Level.INFO, "Print this when viewMap is clicked ");
	    	 Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());
		 }
	@FXML protected void handleSubmitButtonUserAd(ActionEvent event) {
	        actionPrinte.setText("");
	        actionPrinte.setText("Utente");        
	} 
}