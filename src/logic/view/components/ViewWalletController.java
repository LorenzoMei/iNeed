package logic.view.components;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import logic.view.Context;
import logic.view.View;
import logic.view.ViewRegulations;

public class ViewWalletController implements Initializable{
	
	@FXML private Text textToken;

	
	Logger loggerW = Logger.getLogger(this.getClass().getName());
	
	public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 
		 String status = "My location " + locationUser + " my resoursources: " + resourcesUser;
		 loggerW.log(Level.INFO, status);
	   

		}
	
	@FXML protected void handleSubmitButtonRegulationsW(ActionEvent event) {
	     View nextViewU = (View) new ViewRegulations();
	     nextViewU.setProfileName(Context.getReference().getCurrentView().getProfileName());
		 Context.getReference().getCurrentView().setNextView(nextViewU);
		 Context.getReference().goNext();
	    }
}
