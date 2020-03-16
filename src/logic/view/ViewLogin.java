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
import logic.beans.CredentialsBean;
import logic.dao.UserNotFoundException;
import logic.login.LoginController;
import logic.login.WrongPasswordException;


public class ViewLogin extends View implements Initializable {
	
	 String formError = MSGError.ERROR_FORM.getMsg();
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
		String status = "My location " + location + " my resoursources: " + resources;
		logger.log(Level.INFO, status);
		userNameTextField.setPromptText("*Es. Rossi.Mario25");

	}
	 

	@FXML protected void handleSubmitButtonSignUp(ActionEvent event) {
	        actionCancel.setText("");
	        actionLogIn.setText("");
	    	Context.getReference().goNext(GoNextTargets.VIEW_SIGNUP.getStateName());

	}
    
    @FXML protected void handleSubmitButtonCancel(ActionEvent event) {
    	actionLogIn.setText("");
    	passwordTextField.clear();
    	userNameTextField.clear();
        actionCancel.setText("Canceled");
    }
    
    @FXML protected void handleSubmitButtonHelp(ActionEvent event) {
		 actionCancel.setText("");
	     actionCancel.setText("Help Page!");
	 }
    
    @FXML protected void handleSubmitButtonLogIn(ActionEvent event) {
    	
    	logger.log(Level.INFO, "populating textInputFields in viewLogin");
    	
    	CheckEmptyField check = new CheckEmptyField();
    	
    	check.populateTextInputFields(this);
    	
    	
    	logger.log(Level.INFO, "canceling text");

    	actionCancel.setText("");
    	
    	this.textInputFields = check.getTextInputFields();
        
    	
        String username = userNameTextField.getText();
        String passw = passwordTextField.getText();
        
    	logger.log(Level.INFO, "starting iteration on textInputFields of " + textInputFields.size() + " elements" + textInputFields.get(0));

            	
    	for(int i = 0; i < textInputFields.size(); i++) {
        	
        	logger.log(Level.INFO, textInputFields.get(i).getClass().getSimpleName());
        	if(textInputFields.get(i).getText().isEmpty()) {
        		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Empty Fields");
                return;
        	}
        	else {
            	logger.log(Level.INFO, "Sono qui");

        		CredentialsBean data = new CredentialsBean();
            	logger.log(Level.INFO, "Sono qui dopo la bean");

        		LoginController controller = LoginController.getInstance();
            	logger.log(Level.INFO, "Sono qui dopo il controller");

        		data.setUsername(username);
        		data.setPassw(passw);
        		try {
    				controller.login(data);
                	logger.log(Level.INFO, "Sono qui dopo il controller e ho passato i dati");

    			} catch (WrongPasswordException e1) {
    				actionLogIn.setText("Sorry username or password is wrong! ");
            		return;
            		
    			} catch (UserNotFoundException e1) {
                	logger.log(Level.WARNING, e1.toString());
    				actionLogIn.setText("Sorry the user doesn't exist! ");
            		return;

    			}                    	
        		

        		View.setProfileName(username);
            	logger.log(Level.INFO, "In the login username from view is: " + View.getProfileName());
            	logger.log(Level.INFO, "In the login username is: " + data.getUsername());

        		
        		actionLogIn.setText("Logged in, welcome back " + username);
   	    	    Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());

            }

        }

    }
  
}
