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
import logic.login.LoginController;
import logic.view.Context;
import logic.view.View;
import logic.view.ViewFlow;
import logic.view.ViewLogin;
import logic.view.ViewMakeAnAd;
import logic.view.ViewMap;
import logic.view.ViewUser;
import logic.view.ViewValidateAFavor;

public class ToolBarController implements Initializable {


	
	 Logger logger = Logger.getLogger(this.getClass().getName());
//	 ViewProfileBean pBean = new ViewProfileBean();
//	 ViewProfileController viewProfileController =  ViewProfileController.getInstance();
//	 LoginController loginController =  LoginController.getInstance();
//	 LogoutBean lBean = new LogoutBean(); 
//	 public View nextView = (View) new ViewFlow();

	 @FXML private MenuItem profileName;
	 @FXML ImageView logoTB;
	 
	 @Override
		public void initialize(URL location, ResourceBundle resources) {

			String status = "My location " + location + " my resoursources: " + resources;
			logger.log(Level.INFO, status);
			logger.log(Level.INFO, "In ToolBarController the username is: " + Context.getReference().getCurrentView().getProfileName() );
			if(Context.getReference().getCurrentView().getProfileName() != null) {
				profileName.setText(Context.getReference().getCurrentView().getProfileName());
			}
			
		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAdU(ActionEvent event) {
		 View nextView = (View) new ViewMakeAnAd();
		 nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
		Context.getReference().getCurrentView().setNextView(nextView);
	 	Context.getReference().goNext();

			
	 } 
	 @FXML protected void handleSubmitButtonViewFlowU(ActionEvent event) {
		
      	 logger.log(Level.INFO, "Print this when ViewFlow is clicked ");
      	 View nextView = (View) new ViewFlow();
		 nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
      	 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();	

	    }
	 
	 @FXML protected void handleSubmitButtonValidateAFavorU(ActionEvent event) {
		 View nextView = (View) new ViewValidateAFavor();
		 nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
      	 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();
	    }
	 
	 @FXML protected void handleSubmitButtonViewProfileU(ActionEvent event) {
		 View nextView = (View) new ViewUser();
		 nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
		 Context.getReference().getCurrentView().setNextView(nextView);
	 	 Context.getReference().goNext();
	    }
	 
	 @FXML protected void handleSubmitButtonExitU(ActionEvent event) {
	 	
		LogoutBean lBean = new LogoutBean();
		lBean.setUsername(Context.getReference().getCurrentView().getProfileName());
		LoginController.getInstance().logout(lBean);
	 	View nextView = (View) new ViewLogin();
		nextView.setProfileName(null);
	 	lBean.setUsername(nextView.getProfileName());
	 	Context.getReference().getCurrentView().setNextView(nextView);
 		Context.getReference().goNext();
	    }
	 @FXML protected void handleSubmitButtonViewMapU(ActionEvent event) {
	 		
          	 logger.log(Level.INFO, "Print this when viewMap is clicked ");
          	 View nextView = (View) new ViewMap();
    		 nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
     	 	 Context.getReference().getCurrentView().setNextView(nextView);
	    	 Context.getReference().goNext();
		 }
	@FXML protected void handleSubmitButtonUserU(ActionEvent event) {
     	 logger.log(Level.INFO, "User clicked ");

	}
	
}
