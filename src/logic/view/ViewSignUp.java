package logic.view;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Calendar.Builder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import logic.beans.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
 
public class ViewSignUp extends View implements Initializable{
	
	String formError = "FORM ERROR!";
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
	private List<TextInputControl> textInputFields;
    Logger logger = Logger.getLogger(this.getClass().getName());


 
    public  ViewSignUp() {
    	
    	this.textInputFields = new ArrayList<>();
    	this.setFXMLPath("fxml_signup.fxml");
//    	this.setNext("logic.view.ViewLogin");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.log(Level.SEVERE,"My location " + location + " my resoursources: " + resources);

		nameTextField.setPromptText("*Es. Mario");
        surNameTextField.setPromptText("*Es. Rossi");
        userNameTextField.setPromptText("*Es. Rossi.Mario25");
        cityTextField.setPromptText("*Es. Roma");
        emailTextField.setPromptText("*Es. Mario.Ro@jmail.gg");
        datePickerTextField.setPromptText("*Es. dd/mm/yyyy");
	}
	
	public TextField getCityTextField() {
		return cityTextField;
	}
	
	public TextField getEmailTextField() {
		return emailTextField;
	}
	
	public TextField getUserNameTextField() {
		return userNameTextField;
	}
	
	public TextField getNameTextField() {
		return nameTextField;
	}
	
	public TextField getSurNameTextField() {
		return surNameTextField;
	}
	
	
	public TextField getPasswordVTextField() {
		return passwordVTextField;
	}
	
	public TextField getPasswordTextField() {
		return passwordTextField;
	}
	
        
	@FXML protected void handleSubmitButtonLogIn(ActionEvent event) {
		
//		p
		System.out.println("I'm "+this.getClass().getSimpleName()+", setting next to " + this.getNext());
		System.out.println("ViewSignup: next state set to: "+this.getNext());
		Context.getReference().goNext("logic.view.ViewLogin");
//		actionSignIn.setText("");
//        actionCancel.setText("Ciao");
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
        
    	logger.log(Level.SEVERE, "populating textInputFields in viewLogin");
    	
    	CheckEmptyField check = new CheckEmptyField();
    	
    	check.populateTextInputFields(this);
    	
    	logger.log(Level.SEVERE, "Signup after populating");
    	
        this.textInputFields = check.getTextInputFields();
        
    	logger.log(Level.SEVERE, "Signup Populated the textInputFields");


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
        
        if(bDate == null) {
        	showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Insert a Date please!");
            return;
    
        }
        
    	logger.log(Level.SEVERE, "bdate: " + bDate );

        
		int bDay = bDate.get(ChronoField.DAY_OF_MONTH);
		int bMonth = bDate.get(ChronoField.MONTH_OF_YEAR);
		int bYear = bDate.get(ChronoField.YEAR);
		
    	logger.log(Level.SEVERE, "day: " + bDay );

		
		calendarBuilder.setDate(bYear, bMonth, bDay);
		Calendar userBirthDate = calendarBuilder.build();
		

		int todayYear = today.get(Calendar.YEAR);
		int difference = todayYear - bYear;
		
        

		
    	if((difference) < 18 || (bYear > todayYear) ) {
			 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Invalid date, you're too young check regulations ");

    	}
		
        
        if(passw.compareTo(vPsw) != 0) {
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),formError, "Your password doesn't match!");
            return;
    }
    
		for(int i = 0; i < textInputFields.size(); i++) {
		        logger.log(Level.SEVERE, textInputFields.get(i).getClass().getSimpleName());
		        if(textInputFields.get(i).getText().isEmpty()) {
		        	showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service field Error!", "Empty Fields, complete all the obligatoryFileds");
		            return;
		        }
		        else {
		        	
		        }
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
	    		
	    		
	    		this.setNext("logic.view.ViewLogin");
//	    		p
	    		System.out.println("I'm "+this.getClass().getSimpleName()+", setting next to " + this.getNext());
	    		Context.getReference().goNext("logic.view.ViewLogin");
	    		
	    	}
        

    }

}