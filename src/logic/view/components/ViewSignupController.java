package logic.view.components;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Calendar.Builder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import logic.beans.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
import logic.view.Context;
import logic.view.MSG;
import logic.view.View;
import logic.view.ViewLogin;

public class ViewSignupController implements Initializable {
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

    Timeline timeline;
    
    public  void viewSignUpController() {
    	this.textInputFields = new ArrayList<>();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String status = "My location " + location + " my resoursources: " + resources;
		logger.log(Level.INFO, status);

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
		
		View nextViewS =  new ViewLogin();
        Context.getReference().getCurrentView().setNextView(nextViewS);
    	Context.getReference().goNext();

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
        
    	logger.log(Level.INFO, "populating textInputFields in viewLogin");
    	
    	CheckEmptyField check = new CheckEmptyField();
    	
    	check.populateTextInputFields(this);
    	
    	logger.log(Level.INFO, "Signup after populating");
    	
        this.textInputFields = check.getTextInputFields();
        
    	logger.log(Level.INFO, "Signup Populated the textInputFields");


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
        	showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), MSG.ERROR_FORM.getMsg(), "Insert a Date please!");
            return;
    
        }
        
        

        
		int bDay = bDate.get(ChronoField.DAY_OF_MONTH);
		int bMonth = bDate.get(ChronoField.MONTH_OF_YEAR);
		int bYear = bDate.get(ChronoField.YEAR);
		
		final String birthDate = "bdate: " + bDate;
		final String birthDay = "day: " + bDay;
        
    	logger.log(Level.INFO, birthDate);
    	logger.log(Level.INFO, birthDay);

		
		calendarBuilder.setDate(bYear, bMonth, bDay);
		Calendar userBirthDate = calendarBuilder.build();
		

		int todayYear = today.get(Calendar.YEAR);
		int difference = todayYear - bYear;
		
        

		
    	if((difference) < 18 || (bYear > todayYear) ) {
			 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), MSG.ERROR_FORM.getMsg(), "Invalid date, you're too young check regulations ");
			 return;
    	}
		
        
        if(passw.compareTo(vPsw) != 0) {
            showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),MSG.ERROR_FORM.getMsg(), "Your password doesn't match!");
            return;
    }
    
		for (int i = 0; i < textInputFields.size(); i++) {
			logger.log(Level.INFO, textInputFields.get(i).getClass().getSimpleName());
			if (textInputFields.get(i).getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service field Error!",
						"Empty Fields, complete all the obligatoryFileds");
				return;
			} else {
				logger.log(Level.INFO, "0 Empty Fields");

			}
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
			logger.log(Level.WARNING, e1.toString());
			actionSignIn.setText("Sorry " + username + " was already take! Try " + username + "1");
			userNameTextField.setText("");
			return;
		}

		actionSignIn.setText("Signed in, welcome " + username);

		displayDialogS();

    }
    
	 public  void displayDialogS() {
		 
		 	String style = "Tahoma";
		 
	        Stage stage = new Stage();
	        
	        GridPane gridNewWindow = new GridPane();
	        gridNewWindow.setAlignment(Pos.CENTER);
	        gridNewWindow.setHgap(10);
	        gridNewWindow.setVgap(10);
	        gridNewWindow.setPadding(new Insets(10, 10, 10, 10));

	       
	        
	        stage.setTitle("Well Done!");
	        
	        final Separator sepHorizo3 = new Separator();
	        sepHorizo3.setOrientation(Orientation.HORIZONTAL);
	        sepHorizo3.setValignment(VPos.CENTER);
	        sepHorizo3.setPrefHeight(80);
	        GridPane.setConstraints(sepHorizo3, 1, 6);
	        GridPane.setRowSpan(sepHorizo3, 2);
	        gridNewWindow.getChildren().add(sepHorizo3);
	        
	        final Separator sepHoriz4 = new Separator();
	        sepHoriz4.setOrientation(Orientation.HORIZONTAL);
	        sepHoriz4.setValignment(VPos.CENTER);
	        sepHoriz4.setPrefHeight(80);
	        GridPane.setConstraints(sepHoriz4, 1, 5);
	        GridPane.setRowSpan(sepHoriz4, 2);
	        gridNewWindow.getChildren().add(sepHoriz4);

	        Text textSignUpS = new Text("Your're successfully entred in iNeed world!");
	        textSignUpS.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
	        textSignUpS.setFill(Color.CHARTREUSE);
	        textSignUpS.setStrokeWidth(0.5); 
	        textSignUpS.setStroke(Color.BLUE); 
	        textSignUpS.setTextAlignment(TextAlignment.CENTER);
	        GridPane.setConstraints(textSignUpS, 1, 0);
	        GridPane.setHalignment(textSignUpS, HPos.CENTER);
	        gridNewWindow.getChildren().add(textSignUpS);
	        
	        Text textName = new Text(userNameTextField.getText() );
	        textName.setTextAlignment(TextAlignment.CENTER);
	        textName.setFont(Font.font(style, FontWeight.EXTRA_BOLD, 18));
	        GridPane.setConstraints(textName, 1, 6);
	        GridPane.setHalignment(textName, HPos.CENTER);
	        gridNewWindow.getChildren().add(textName);

	        
	        Text textWlecome = new Text("WELCOME");
	        textWlecome.setFont(Font.font(style, FontWeight.BOLD, 14));
	        textWlecome.setTextAlignment(TextAlignment.CENTER);
	        GridPane.setConstraints(textWlecome, 1, 4);
	        GridPane.setHalignment(textWlecome, HPos.CENTER);
	        gridNewWindow.getChildren().add(textWlecome);
	        
	        
	        
	        
	        Image logo = new Image(getClass().getResourceAsStream("/media/Red-Logomark.png"));

	        final ImageView logo1 = new ImageView(logo);
	        logo1.setFitHeight(70);
	        logo1.setFitWidth(70);
	        VBox vBoxSig = new VBox();
			vBoxSig.setMaxSize(30, 30);
			vBoxSig.getChildren().addAll(logo1);
	        GridPane.setHalignment(vBoxSig, HPos.CENTER);
	        GridPane.setConstraints(vBoxSig, 1, 13);
	        gridNewWindow.getChildren().add(vBoxSig);
	        gridNewWindow.setStyle("-fx-background-color:  #d5e8f5");
	        

	        
	        Text textR = new Text("You're going  redirected to login page...");
	        textR.setFont(Font.font(style, FontWeight.LIGHT, 12));
	        textR.setTextAlignment(TextAlignment.CENTER);
	        GridPane.setConstraints(textR, 1, 8);
	        GridPane.setHalignment(textR, HPos.CENTER);
	        gridNewWindow.getChildren().add(textR);
	        
	        
	        
	        ProgressBar progress = new ProgressBar();
	        progress.setMinWidth(200);
	        progress.setMaxWidth(Double.MAX_VALUE);
	        IntegerProperty seconds = new SimpleIntegerProperty();
	        progress.progressProperty().bind(seconds.divide(2.0));
	        timeline = new Timeline(
	            new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
	            new KeyFrame(Duration.seconds(2), e-> {
	            	

		        	logger.log(Level.INFO, "Time Expired");
		        	timeline.stop();
		        	stopTime();
		        	stage.close();
		        	
		        	
		        	
	            }, new KeyValue(seconds, 2))   
	        );
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play();
	        
    
	        GridPane.setHalignment(progress, HPos.CENTER);
	        GridPane.setConstraints(progress, 1, 9);
	        gridNewWindow.getChildren().add(progress);
	        
		    Scene scene = new Scene(gridNewWindow, 500, 375);
	        stage.setScene(scene);
	        stage.show();
		}
	 private void stopTime() {
     	View nextViewSS =  new ViewLogin();
         Context.getReference().getCurrentView().setNextView(nextViewSS);
     	Context.getReference().goNext();
	 }
    protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
