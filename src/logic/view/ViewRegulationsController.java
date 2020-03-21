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
import logic.beans.ViewProfileBean;
import logic.viewprofile.ViewProfileController;

public class ViewRegulationsController implements Initializable{
	
	@FXML private Text testerR;
	@FXML private MenuItem profileNameR;
	ViewProfileBean pBean = new ViewProfileBean();
 	ViewProfileController pController =  ViewProfileController.getInstance();

 	
 	Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
    }
    
    
    
    @FXML protected void handleSubmitButtonReport(ActionEvent event) {
    	testerR.setText("");
    	testerR.setText("Report");
	
    }
    
    @FXML protected void handleSubmitButtonContactUs(ActionEvent event) {
    	testerR.setText("");
    	testerR.setText("Contact");
	
    }


}
