package logic.view;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.ResourceBundle;
import javafx.stage.Window;
import logic.beans.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
 
public class ViewSignUp extends View implements Initializable{
	
    @FXML private Text actionSignIn;
    @FXML private Text actionCancel;
    @FXML private TextField nameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField surNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField passwordVTextField;
    @FXML private Hyperlink facebookHyperLink;
    @FXML private Hyperlink logInHyperLink;
    @FXML private DatePicker datePickerTextField;
    @FXML private GridPane grid;
 
    public  ViewSignUp() {
    	this.setFXMLPath("fxml_signup.fxml");
    	this.setNext("logic.view.ViewLogin");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameTextField.setPromptText("*Es. Mario");
        surNameTextField.setPromptText("*Es. Rossi");
        userNameTextField.setPromptText("*Es. Rossi.Mario25");
        cityTextField.setPromptText("Es. Roma");
        emailTextField.setPromptText("*Es. Mario.Ro@jmail.gg");
        datePickerTextField.setPromptText("*Es. dd/mm/yyyy");
	}
	
        
	@FXML protected void handleSubmitButtonBack(ActionEvent event) {
		//TODO buttonBack functionalities
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
    	datePickerTextField.getEditor();
    	surNameTextField.clear();
        actionSignIn.setText("");
        actionCancel.setText("Canceled");
        datePickerTextField.getEditor().clear();
        

    }
    
    @FXML protected void handleSubmitButtonSignUp(ActionEvent event) {
        actionCancel.setText("");        

        String username = userNameTextField.getText();
        String passw = passwordTextField.getText();
        String vPsw = passwordVTextField.getText();
        String city = cityTextField.getText();
        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String surName = surNameTextField.getText();
        Calendar  today = Calendar.getInstance();   
        Builder calendarBuilder  = new Calendar.Builder();
        LocalDate bDate = datePickerTextField.getValue();
        
        
		int bDay = bDate.get(ChronoField.DAY_OF_MONTH);
		int bMonth = bDate.get(ChronoField.MONTH_OF_YEAR);
		int bYear = bDate.get(ChronoField.YEAR);
		
		calendarBuilder.setDate(bYear, bMonth, bDay);
		Calendar userBirthDate = calendarBuilder.build();
		
		int todayDay = today.get(Calendar.DATE);
		int todayMonth = today.get(Calendar.MONTH) + 1;
		int todayYear = today.get(Calendar.YEAR);
		int difference = todayYear - bYear;
		
		
        if((difference) <= 18) {
        	 if(((difference) == 18) && (bMonth <= todayMonth)) {
        		 if((bMonth == todayMonth) && (bDay <= todayDay)) {
        			 if(bDay == todayDay) {
        				 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Happy Birthday!", "Just in time sir: " + username);
        			 }
        			 else {
            			 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "You are to young to join us, see you in " + (bDay - todayDay) + " days!");

        			 }
        		 }
        		 else {
        			 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "You are to young to join us, see you in " + (bMonth -todayMonth) + " months!");
                     return;
        		 }		 
        	 }
        	 else{
        		 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "You are to young to join us now, see you soon!");
                 return;
        	 }
        	
        }
        
        if((bYear > todayYear) || (bYear == todayYear && bMonth > todayMonth) || (bYear == todayYear && bMonth == todayMonth && bDay > todayDay) ) {
        	showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Are you from future?", "Tell me tomorrow lotterys numbers! NOW!");
            return;
        }

        
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
    		
        	SignUpBean usersBean = new SignUpBean();
    		
    		
    		usersBean.setUsername(username);
    		usersBean.setPassword(passw);
    		usersBean.setCity(city);
    		usersBean.setEmail(email);
    		usersBean.setBirthDate(userBirthDate);
    		usersBean.setName(name);
    		usersBean.setSurName(surName);
    		
    		try {
				SignUpController.getInstance().signUp(usersBean);
			} catch (UsernameAlreadyTakenException e1) {
				e1.printStackTrace();
				actionSignIn.setText("Sorry " + username + " was already take! Try " + username + "1");
				return;
			}
    		
    		
    		actionSignIn.setText("Signed in, welcome " + username);
    		
    		
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


	@Override
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