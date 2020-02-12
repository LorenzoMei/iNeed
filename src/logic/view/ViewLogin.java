package logic.view;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Window;
import logic.beans.CredentialsBean;
import logic.dao.UserNotFoundException;
import logic.login.LoginController;
import logic.login.WrongPasswordException;


public class ViewLogin extends View implements Initializable {
	
	 String formError = "FORM ERROR!";
	 @FXML private Text actionLogIn;
	 @FXML private Text actionCancel;
	 @FXML private TextField userNameTextField;
	 @FXML private PasswordField passwordTextField;
	 @FXML private GridPane grid;
	 @FXML private Hyperlink facebookHyperLink;
	 private List<TextInputControl> textInputFields;
  	 Logger logger = Logger.getLogger(this.getClass().getName());


	public ViewLogin() {
		this.textInputFields = new ArrayList<>();		
		this.setFXMLPath("fxml_login.fxml");    	
	}
	
	
	public TextField getUserNameTextField() {
		return userNameTextField;
	}
	
	public PasswordField getPasswordTextField() {
		return passwordTextField;
	}
	
	 
	public void initialize(URL location, ResourceBundle resources) {
	        userNameTextField.setPromptText("*Es. Rossi.Mario25");

	}
	 

	@FXML protected void handleSubmitButtonSignUp(ActionEvent event) {
	        actionCancel.setText("");
	        actionLogIn.setText("");
	}
    
    @FXML protected void handleSubmitButtonCancel(ActionEvent event) {
    	actionLogIn.setText("");
    	passwordTextField.clear();
    	userNameTextField.clear();
        actionCancel.setText("Canceled");
    }
    
    
    @FXML protected void handleSubmitButtonLogIn(ActionEvent event) {
    	
    	logger.log(Level.SEVERE, "populating textInputFields in viewLogin");
    	
    	CheckEmptyField check = new CheckEmptyField();
    	
    	check.populateTextInputFields(this);
    	
    	
    	logger.log(Level.SEVERE, "canceling text");

    	actionCancel.setText("");
    	
    	this.textInputFields = check.getTextInputFields();
        
    	
        String username = userNameTextField.getText();
        String passw = passwordTextField.getText();
        
    	logger.log(Level.SEVERE, "starting iteration on textInputFields of " + textInputFields.size() + " elements" + textInputFields.get(0));

            	
    	for(int i = 0; i < textInputFields.size(); i++) {
        	
        	logger.log(Level.SEVERE, textInputFields.get(i).getClass().getSimpleName());
        	if(textInputFields.get(i).getText().isEmpty()) {
        		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Empty Fields");
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
    				actionLogIn.setText("Sorry username or password is wrong! ");
            		return;
            		
    			} catch (UserNotFoundException e1) {
    				actionLogIn.setText("Sorry the user doesn't exist! ");
            		return;

    			}                    	
        		
        		actionLogIn.setText("Logged in, welcome back " + username);
   	    	    Context.getReference().goNext("logic.view.ViewFlow");

            }

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

	@Override
	public void goNext(String viewName) {
		View nextView;
		try {
//			p
			System.out.println("ViewLogin: attempting to set nextState as "+viewName);
			nextView = (View) Class.forName(viewName).newInstance();
//			p
			System.out.println("ViewLogin: nextView is "+nextView.getClass().getSimpleName());
			nextView.setPrevious(this);
			
			Context.getReference().setCurrentView(nextView);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.toString() + " Error in goNext");

		}

	}
  
}
