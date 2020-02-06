package logic.view.signup;

import logic.signup.SignUpBean;
import logic.signup.SignUpController;
import logic.signup.UsernameAlreadyTakenException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;


public class ViewSignUp extends Application {
	 public static void main(String[] args) {
	    	
		    
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage)  { 
	        primaryStage.setTitle("iNeed Registration Form");
	        
	        primaryStage.show();
	    
		        
		    GridPane grid = new GridPane();
		    grid.setAlignment(Pos.CENTER);
		    grid.setHgap(10);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(50, 50, 50, 50));
		
		    
		    Text scenetitle1 = new Text("We are iNeed family!\n	  Join Us!");
		    scenetitle1.setId("welcome-text");
		    grid.add(scenetitle1, 2, 1, 3, 1);
		      
		    
		    final WebView browser = new WebView();
	        final WebEngine webEngine = browser.getEngine();
	        
		    Hyperlink link = new Hyperlink();
		    link.setText("facebook");
		    link.setId("link-fb");
		    
		    
		    link.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent e) {
		        	webEngine.load("https://www.facebook.com/login/");
		    	    grid.add(browser, 2, 20);

		        }
		    });
		    
		    HBox hbBtnFB = new HBox(10);
		    hbBtnFB.setAlignment(Pos.BOTTOM_LEFT);
		    hbBtnFB.getChildren().add(link);
		    grid.add(hbBtnFB, 0, 7);
		    
		    Separator separator1 = new Separator();
		    grid.add(separator1, 0, 8);
		    Label or = new Label("Or");
		    grid.add(or, 0, 9);
		    
		    Separator separator2 = new Separator();
		    grid.add(separator2, 0, 10);
		    
		    Label name = new Label("			  Full Name:");
		    grid.add(name, 0, 12);
		    
		
		    TextField nameTextField = new TextField();
		    nameTextField.setPrefHeight(40);
	        nameTextField.setPrefWidth(50);
	        nameTextField.setPromptText("*Es. Mario Rossi");
		    grid.add(nameTextField, 1, 12);
		    
		    
		    Label userNamelabel = new Label("UserName:");
		    grid.add(userNamelabel, 2, 12);
		    
		    TextField userNameTextField = new TextField();
	        userNameTextField.setPromptText("*Es. Rossi.Mario");
		    grid.add(userNameTextField, 3, 12);
		    
		    
		    Label city = new Label("City:");
		    grid.add(city, 4, 12);
		
		    TextField cityTextField = new TextField();
	        cityTextField.setPromptText("Es. Roma");
		    grid.add(cityTextField, 5, 12);
		    
		    
		    Label email = new Label("				Email:");
		    grid.add(email, 0, 14);
		
		    TextField emailTextField = new TextField();
	        emailTextField.setPromptText("*Es. Mario.Ro@jmail.gg");
		    grid.add(emailTextField, 1, 14);
		 
		
		    Label pw = new Label("Password:");
		    grid.add(pw, 2, 14);
		
		    PasswordField pwBox = new PasswordField();
		    grid.add(pwBox, 3, 14);
		    
		    Label pwC = new Label("Confirm password:");
		    grid.add(pwC, 4, 14);
		
		    PasswordField pwCBox = new PasswordField();
		    grid.add(pwCBox, 5, 14);

		    
		    Button btnCancel = new Button("Cancel");
		    HBox hbBtnC = new HBox(10);
		    hbBtnC.setAlignment(Pos.BOTTOM_LEFT);
		    hbBtnC.getChildren().add(btnCancel);
		    grid.add(hbBtnC, 1, 18);
		    
		    Button btnSub = new Button("Subscribe");
		    HBox hbBtnS = new HBox(10);
		    hbBtnS.setAlignment(Pos.BOTTOM_RIGHT);
		    hbBtnS.getChildren().add(btnSub);
		    grid.add(hbBtnS, 5, 18);
		    
		    Text actionCancel = new Text();
		    grid.add(actionCancel, 0, 2);
		    
		    
		    Text actionSignIn = new Text();
		    grid.add(actionSignIn, 0, 2);
		    
		   
		    
		    Scene scene = new Scene(grid, 1100, 875);
		    primaryStage.setScene(scene);
		    scene.getStylesheets().add(ViewSignUp.class.getResource("Register.css").toExternalForm());
		    
		    
		    //Controlling the cancel bottom, that cleans the page.
		    btnCancel.setOnAction(new EventHandler<ActionEvent>() {
		    	 
		        @Override
		        public void handle(ActionEvent e) {
		        	nameTextField.clear();
		        	userNameTextField.clear();
		        	emailTextField.clear();
		        	cityTextField.clear();
		        	pwBox.clear();
		        	pwCBox.clear();
		        	actionCancel.setId("actionCancel");
		            actionSignIn.setText(null);
		            actionCancel.setText("Canceled");


		        }});
		    
		    //controlling the singinButton that can insert the data
		    btnSub.setOnAction(new EventHandler<ActionEvent>() {
		    	 
		        @Override
		        public void handle(ActionEvent e) {
		            actionCancel.setText(null);
		            String username = nameTextField.getText();
		            String passw = pwBox.getText();
		            String vPsw = pwCBox.getText();
		        	actionSignIn.setId("actionSignIn");
		        	
		        	if(nameTextField.getText().isEmpty()) {//If isn't inserted a Name
	                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Name!");
	                    return;
	                }
		        	
		        	if(userNameTextField.getText().isEmpty()) {//If isn't inserted a SurName
	                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your SurName!");
	                    return;
	                }
		        	
		        	if(emailTextField.getText().isEmpty()) {//If isn't inserted an Email
	                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Email!");
	                    return;
	                }

		        	
		        	if(pwBox.getText().isEmpty()) {//If isn't inserted a Password
	                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "Please enter your Password!");
	                    return;
	                }

		        	
		        	if(pwCBox.getText().isEmpty()) {//If isn't inserted a Password verification
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
		        		
		        		try {
							SignUpController.getInstance().signUp(data);
						} catch (UsernameAlreadyTakenException e1) {
							e1.printStackTrace();
							actionSignIn.setText("Sorry " + username + " was already take! Try " + username + "1");
							return;
						}
		        		
		        		
		        		actionSignIn.setText("Signed in, welcome " + username);
		        	}

		        }});
		    
		    
		    
	    
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
