package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Window;
import logic.beans.CredentialsBean;
import logic.dao.UserNotFoundException;
import logic.login.LoginController;
import logic.login.WrongPasswordException;


public class ViewLogin extends View implements Initializable {
	
	public ViewLogin() {
		this.setFXMLPath("fxml_login.fxml");
    	this.setNext("logic.view.ViewFlow");
	}
	
	  //ScreensController myController;
	
	 @FXML private Text actionLogIn;
	 @FXML private Text actionCancel;
	 @FXML private TextField userNameTextField;
	 @FXML private PasswordField passwordTextField;
	 @FXML private GridPane grid;

    
	 
	 @FXML private Hyperlink facebookHyperLink;
	 
	 public void initialize(URL location, ResourceBundle resources) {
	        userNameTextField.setPromptText("*Es. Rossi.Mario25");

		}
	 
	/* public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }*/

	@FXML protected void handleSubmitButtonBack(ActionEvent event) {
	        actionCancel.setText("");
	        actionLogIn.setText("");
	      //  myController.setScreen(ScreensFramework.screen3ID);
	        
	}
    
    @FXML protected void handleSubmitButtonCancel(ActionEvent event) {
    	actionLogIn.setText("");
    	passwordTextField.clear();
    	userNameTextField.clear();
        actionCancel.setText("Canceled");
    }
    
    @FXML protected void handleSubmitButtonLogIn(ActionEvent event) {
        actionCancel.setText("");
        
        String username = userNameTextField.getText();
        String passw = passwordTextField.getText();

    	if(userNameTextField.getText().isEmpty()) {//If isn't inserted an UserName
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service Error!", "Please enter your UserName!");
            return;
        }
    	
    	if(passwordTextField.getText().isEmpty()) {//If isn't inserted a Password
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service Error!", "Please enter your Password!");
            return;
        }
    	
    	else {
    		
    		CredentialsBean data = new CredentialsBean();
    		LoginController controller = LoginController.getInstance();
    		data.setUsername(username);
    		data.setPassw(passw);
    		try {
				controller.login(data);
			} catch (WrongPasswordException e1) {
				e1.printStackTrace();
				actionLogIn.setText("Sorry username or password is wrong! ");
        		return;
        		
			} catch (UserNotFoundException e1) {
				e1.printStackTrace();
				actionLogIn.setText("Sorry the user doesn't exist! ");
        		return;

			}

        	actionLogIn.setText("Logged in, welcome back " + username);
    		Context.getReference().goNext();

    	}

    }
    
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void goNext() {
		View nextView;
		try {
			nextView = (View) Class.forName(this.getNext()).newInstance();
			nextView.setPrevious(this);
			Context.getReference().setCurrentView(nextView);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
    
}
