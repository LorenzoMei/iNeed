package logic.view;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Window;
import logic.beans.LogoutBean;
import logic.beans.PublishAnAdBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.publishanad.PublishAnAdController;

public class ViewMakeAnAdController implements Initializable {
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
     
 	 private View nextViewM;

	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationAd, ResourceBundle resourcesAD) {
		 titleTextField.setPromptText("*Es. I need a bed this weekend...");
//		 profileName.setText(View.getProfileName());
		 othersTextField.setEditable(false);
		 String status = "My location " + locationAd + " my resoursources: " + resourcesAD;
		 logger.log(Level.SEVERE, status);
		}
	 
	 
	 public static void setType(String type) {
	 		ViewMakeAnAdController.type = type;

	 	}
	 
	 
	     public static String getType() {
	    	 return ViewMakeAnAdController.type;
	     }
	     
	     public static void setOther(boolean other) {
	    	 ViewMakeAnAdController.other = other;

		  	}
	     
	     public static boolean getOther() {
	     	 return ViewMakeAnAdController.other;
	     }
	     
	     
	     public static void setCategory(String category) {
	    	 ViewMakeAnAdController.category = category;

	  	}
	     
	     public static String getCategory() {
	     	 return ViewMakeAnAdController.category;
	    }
	 

	 @FXML protected void handleSubmitButtonRequest(ActionEvent event) {
		 ViewMakeAnAdController.setType("Richiesta");
		 typeButton.setText("Request");
	 }
	 
	 @FXML protected void handleSubmitButtonOffer(ActionEvent event) {
		 ViewMakeAnAdController.setType("Offerta");
		 typeButton.setText("Offer");

	 }
	 
	 @FXML protected void handleSubmitButtonElectronics(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Electronics");
		 categoryButton.setText("Electronics");
	 }
	 
	 @FXML protected void handleSubmitButtonHydraulic(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Hydraulic");
		 categoryButton.setText("Hydraulic");
	 }
	 
	 @FXML protected void handleSubmitButtonGardering(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Gardering");
		 categoryButton.setText("Gardering");
	 }
	 
	 @FXML protected void handleSubmitButtonInformatic(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Informatic");
		 categoryButton.setText("Informatic");
	 }
	 
	 @FXML protected void handleSubmitButtonBed(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Bed sharing");
		 categoryButton.setText("Bed sharing");

	 }
	 
	 @FXML protected void handleSubmitButtonOther(ActionEvent event) {
		 categoryButton.setText("Other");
		 othersTextField.setEditable(true);
		 ViewMakeAnAdController.setOther(true);
	 }
	 
	 
	 @FXML protected void handleSubmitButtonSend(ActionEvent event) {
		 actionPrinte.setText("");
		
		 logger.log(Level.INFO,  "il titolo e': " + ViewMakeAnAdController.getType() );
		 
		 if(ViewMakeAnAdController.getType() == null || titleTextField.getText().isEmpty()) {
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
			     adBean.setCategory(ViewMakeAnAdController.getCategory());   	 
		     }
		     
		     else {
		    	 adBean.setCategory(othersTextField.getText());
		     }
			 
				logger.log(Level.INFO,  "il titolo e': " + ViewMakeAnAdController.getType() );
	
		     
		     adBean.setType(ViewMakeAnAdController.getType());
		     adBean.setTitle(titleTextField.getText());
		     adBean.setBody(bodyTextArea.getText());
		     adBean.setOwnerUsername(nextViewM.getProfileName());
		     
		    
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
	     nextViewM = (View) new ViewRegulations();
        Context.getReference().getCurrentView().setNextView(nextViewM);
    	Context.getReference().goNext();
	     
	 }
	 
	 protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
}
