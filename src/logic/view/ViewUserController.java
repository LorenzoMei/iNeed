package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.viewprofile.ViewProfileController;

public class ViewUserController  implements Initializable  {

	 @FXML private Text nameText;
	 @FXML private Text cityText;
	 @FXML private Text surnameText;
	 @FXML private Text emailText;
	 @FXML private Text actionPrinter;
	 @FXML private GridPane grid;
	 @FXML private MenuItem profileName;
	 ViewProfileController viewProfileController =  ViewProfileController.getInstance();
	 LoginController loginController =  LoginController.getInstance();
	 LogoutBean lBean = new LogoutBean();
	 ViewProfileBean pBean = new ViewProfileBean();

	 private View nextViewU;
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationUser, ResourceBundle resourcesUser) {
		 
		 String status = "My location " + locationUser + " my resoursources: " + resourcesUser;
		 logger.log(Level.INFO, status);
	   
		 nextViewU = (View) new ViewUser();

		 pBean.setRequestedUsername(nextViewU.getProfileName());
		 logger.log(Level.INFO, "initializing " + this.getClass().getSimpleName());
		 
		 viewProfileController.loadProfile(pBean);
		 logger.log(Level.INFO, "user in bean is " + pBean.getName());
		 
		 String name = pBean.getName();
		 String city = pBean.getCity();
		 String surname = pBean.getSurName();
		 String email = pBean.getEmail();
		 
		 nameText.setText(name);
		 cityText.setText(city);
		 surnameText.setText(surname);
		 emailText.setText(email);
		}

	
	 
	 @FXML protected void handleSubmitButtonGallery(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Gallery");			
	    }
	 @FXML protected void handleSubmitButtonCurriculum(ActionEvent event) {
			 actionPrinter.setText("");
		     actionPrinter.setText("go to Curriculum");
		     nextViewU = (View) new ViewCurriculumMainPage();
			Context.getReference().getCurrentView().setNextView(nextViewU);
			Context.getReference().goNext();

	    }
	 
	 @FXML protected void handleSubmitButtonFavorites(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Favorites");
	    }
	 
	 @FXML protected void handleSubmitButtonRatings(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("vado su Raitings");
	    }
	 
	 @FXML protected void handleSubmitButtonWallet(ActionEvent event) {
		 actionPrinter.setText("");
	     actionPrinter.setText("go to Wallet");
	    }
	 
	
}

