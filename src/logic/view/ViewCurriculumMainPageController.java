package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.viewprofile.ViewProfileController;

public class ViewCurriculumMainPageController implements Initializable{
	@FXML private Text tester;
	@FXML private MenuItem profileName;
	ViewProfileBean pBean = new ViewProfileBean();
 	ViewProfileController pController =  ViewProfileController.getInstance();
 	LoginController loginController =  LoginController.getInstance();
	LogoutBean lBean = new LogoutBean();
	
 	Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
    }
    
   
    
    @FXML protected void handleSubmitButtonViewCL(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Cover Letter");
	
    }
    
    @FXML protected void handleSubmitButtonDonwloadCL(ActionEvent event) {
    	tester.setText("");
    	tester.setText("download Cover Letter");
	
    }
    
    @FXML protected void handleSubmitButtonViewLOA(ActionEvent event) {
    	tester.setText("");
    	tester.setText("View Letter Of applications");
	
    }
    
    @FXML protected void handleSubmitButtonDownloadLOA(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Download Letter Of applications");
	
    }
    
    @FXML protected void handleSubmitButtonDownloadCV(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Download CV");
	
    }
    
    @FXML protected void handleSubmitButtonViewCV(ActionEvent event) {
    	tester.setText("");
    	tester.setText("View CV");
	
    }
    
    @FXML protected void handleSubmitButtonContactUs(ActionEvent event) {
    	tester.setText("");
    	tester.setText("Contact");
	
    }

}
