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
	
	@FXML private Text testerR;
	@FXML private MenuItem profileNameR;
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
		 profileNameR.setText(View.getProfileName());
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
    }
    
    @FXML protected void handleSubmitButtonViewFlowR(ActionEvent event) {
   	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());
    }
    
    @FXML protected void handleSubmitButtonUserR(ActionEvent event) {
    	testerR.setText("");
    	testerR.setText("User");
	
    }
    
    @FXML protected void handleSubmitButtonReport(ActionEvent event) {
    	testerR.setText("");
    	testerR.setText("Report");
	
    }
    
    @FXML protected void handleSubmitButtonContactUs(ActionEvent event) {
    	testerR.setText("");
    	testerR.setText("Contact");
	
    }

    @FXML protected void handleSubmitButtonViewProfileR(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonExitR(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonViewMapR(ActionEvent event) {
		 testerR.setText("");
	 	Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());

		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAdR(ActionEvent event) {
		 testerR.setText("MakeAnAd");
		 Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());

	 }
	 
	 
	 @FXML protected void handleSubmitButtonValidateAFavorR(ActionEvent event) {
		 testerR.setText("ValidateAFavor");
	    }
	 

}