package logic.view.signup;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Window;
import logic.signup.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
 
public class ViewSignUpController implements Initializable{
	
    @FXML private Text actionSignIn;
    @FXML private Text actionCancel;
    @FXML private TextField nameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField surNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField cellTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField passwordVTextField;
    @FXML private Hyperlink facebookHyperLink;
    @FXML private Hyperlink logInHyperLink;
    
    @FXML private GridPane grid;
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameTextField.setPromptText("*Es. Mario");
        surNameTextField.setPromptText("*Es. Rossi");
        userNameTextField.setPromptText("*Es. Rossi.Mario25");
        cityTextField.setPromptText("Es. Roma");
        emailTextField.setPromptText("*Es. Mario.Ro@jmail.gg");
        cellTextField.setPromptText("Es. Roma");

	}
        
	@FXML protected void handleSubmitButtonBack(ActionEvent event) {
		actionSignIn.setText("");
        actionCancel.setText("Ciao");
    }
	
    @FXML protected void handleSubmitButtonCancel(ActionEvent event) {
    	nameTextField.clear();
    	userNameTextField.clear();
    	emailTextField.clear();
    	cityTextField.clear();
    	passwordTextField.clear();
    	passwordVTextField.clear();
    	cellTextField.clear();
    	surNameTextField.clear();
        actionSignIn.setText("");
        actionCancel.setText("Canceled");
        

    }
    
    @FXML protected void handleSubmitButtonSignUp(ActionEvent event) {
        actionCancel.setText("");        

        String username = userNameTextField.getText();
        String passw = passwordTextField.getText();
        String vPsw = passwordVTextField.getText();
        String city = cityTextField.getText();
        String email = emailTextField.getText();
        
        System.out.println(nameTextField.getText());
        
        if(nameTextField.getText().isEmpty()) {//If isn't inserted a Name
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Name!");
            return;
        }
        
        if(surNameTextField.getText().isEmpty()) {//If isn't inserted a SurName
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your SurName!");
                return;
        }
        
        if(userNameTextField.getText().isEmpty()) {//If isn't inserted a userName
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your SurName!");
            return;
       }
        	
        if(emailTextField.getText().isEmpty()) {//If isn't inserted an Email
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Email!");
                return;
        }

        	
        if(passwordTextField.getText().isEmpty()) {//If isn't inserted a Password
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Password!");
                return;
        }

        	
        if(passwordVTextField.getText().isEmpty()) {//If isn't inserted a Password verification
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please verify your password!");
                return;
        }
        	
        if(passw.compareTo(vPsw) != 0) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Your password doesn't match!");
                return;
        }
        
        else {
    		
    		SignUpBean data = new SignUpBean();
    		data.setUsername(username);
    		data.setPassword(passw);
    		data.setCity(city);
    		data.setEmail(email);
    		
    		try {
				SignUpController.getInstance().signUp(data);
			} catch (UsernameAlreadyTakenException e1) {
				e1.printStackTrace();
				actionSignIn.setText("Sorry " + username + " was already take! Try " + username + "1");
				return;
			}
    		
    		
    		actionSignIn.setText("Signed in, welcome " + username);
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



}