package logic.view.components;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.view.Context;
import logic.view.View;
import logic.view.ViewFlow;
import logic.view.ViewLogin;
import logic.view.ViewMakeAnAd;
import logic.view.ViewMap;
import logic.view.ViewUser;
import logic.view.ViewValidateAFavor;
import logic.viewprofile.ViewProfileController;

public class ToolBarController implements Initializable {


	
	 Logger logger = Logger.getLogger(this.getClass().getName());
	 ViewProfileBean pBean = new ViewProfileBean();
	 ViewProfileController viewProfileController =  ViewProfileController.getInstance();
	 LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean(); 
	 public View nextView = (View) new ViewFlow();
//	 DropShadow dropShadow = new DropShadow(10, 0, 0, Color.BLACK);
//	 InnerShadow innerShadow = new InnerShadow(10, 0, 0, Color.BLACK);

//	 @FXML private HBox validateAFavorHBox;
//	 @FXML private HBox viewFlowHBox;
	 @FXML private MenuItem profileName;
	 @FXML ImageView logoTB;
	 
	 @Override
		public void initialize(URL location, ResourceBundle resources) {

			String status = "My location " + location + " my resoursources: " + resources;
			logger.log(Level.INFO, status);
			logger.log(Level.INFO, "In ToolBarController the username is: " + nextView.getProfileName() );
			if(nextView.getProfileName() != null) {
				profileName.setText(nextView.getProfileName());
			}
			
		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAdU(ActionEvent event) {
		 nextView = (View) new ViewMakeAnAd();
		Context.getReference().getCurrentView().setNextView(nextView);
	 	Context.getReference().goNext();

			
	 } 
	 @FXML protected void handleSubmitButtonViewFlowU(ActionEvent event) {
		
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
      	 nextView = (View) new ViewFlow();
      	 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();	

	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavorU(ActionEvent event) {
		 nextView = (View) new ViewValidateAFavor();
      	 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfileU(ActionEvent event) {
		 nextView = (View) new ViewUser();
		 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();
	    }
	 
	 @FXML protected void handleSubmitButtonExitU(ActionEvent event) {
	 	loginController.logout(lBean);
	 	nextView = (View) new ViewLogin();
	 	lBean.setUsername(nextView.getProfileName());
	 	Context.getReference().getCurrentView().setNextView(nextView);
 		Context.getReference().goNext();
	    }
	 @FXML protected void handleSubmitButtonViewMapU(ActionEvent event) {
	 		
          	 logger.log(Level.INFO, "Print this when viewMap is clicked ");
          	 nextView = (View) new ViewMap();
     	 	 Context.getReference().getCurrentView().setNextView(nextView);
	    	 Context.getReference().goNext();
		 }
	@FXML protected void handleSubmitButtonUserU(ActionEvent event) {
      
	}
	
}
