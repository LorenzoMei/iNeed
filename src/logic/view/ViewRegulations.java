package logic.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import logic.beans.ViewProfileBean;
import logic.viewprofile.ViewProfileController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewRegulations  extends View implements Initializable {
	
	@FXML private Text tester;
	@FXML private MenuItem profileName;
	ViewProfileBean pBean = new ViewProfileBean();
 	ViewProfileController pController =  ViewProfileController.getInstance();
	
 	Logger logger = Logger.getLogger(this.getClass().getName());

	public  ViewRegulations() {	    	
	    	this.setFXMLPath("fxml_regulations.fxml");
	    	pBean.setRequestedUsername(View.getProfileName());
			pController.loadProfile(pBean);

	    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//		 profileName.setText(View.getProfileName());
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
    }
    
    @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
   	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());
    }
    
    @FXML protected void handleSubmitButtonUser(ActionEvent event) {
    	tester.setText("");
    	tester.setText("User");
	
    }
    
    @FXML protected void handleSubmitButtonReport(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Report");
	
    }
    
    @FXML protected void handleSubmitButtonContactUs(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Contact");
	
    }

    @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
		 tester.setText("");
	 	Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());

		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 tester.setText("MakeAnAd");
		 Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());

	 }
	 
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 tester.setText("ValidateAFavor");
	    }
	 

}
