package logic.view.login;

import logic.beans.CredentialsBean;
import logic.login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;




public class ViewLogin extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to iNeed");
        
        primaryStage.show();
    
	        
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	
	    
	    Text scenetitle = new Text("Welcome To iNeed");
	    scenetitle.setId("welcome-text");
	    grid.add(scenetitle, 0, 0, 2, 1);
	
	    Label userName = new Label("UserName:");
	    grid.add(userName, 0, 2);
	
	    TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("*Es. Mario.Rossi.23");
	    grid.add(userNameTextField, 1, 2);
	
	    Label pw = new Label("Password:");
	    grid.add(pw, 0, 4);
	
	    PasswordField pwBox = new PasswordField();
	    grid.add(pwBox, 1, 4);
	
	    
	    Button btnCancel = new Button("Cancel");
	    HBox hbBtnC = new HBox(10);
	    hbBtnC.setAlignment(Pos.BOTTOM_LEFT);
	    hbBtnC.getChildren().add(btnCancel);
	    grid.add(hbBtnC, 0, 8);
	    
	    Text actionCancel = new Text();
	    grid.add(actionCancel, 1, 12);
	   
	
	    Button btnSignIn = new Button("Sign in");
	    HBox hbBtnSI = new HBox(10);
	    hbBtnSI.setAlignment(Pos.BOTTOM_RIGHT);
	    hbBtnSI.getChildren().add(btnSignIn);
	    grid.add(hbBtnSI, 1, 8);
	    
	    Text actionSignIn = new Text();
	    grid.add(actionSignIn, 1, 12);
	    
	   
	    
	    Scene scene = new Scene(grid, 700, 575);
	    primaryStage.setScene(scene);
	    scene.getStylesheets().add
	     (ViewLogin.class.getResource("Login.css").toExternalForm());
	    
	    
	    //Controlling the cancel bottom, that cleans the page.
	    btnCancel.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	userNameTextField.clear();
	        	pwBox.clear();
	        	actionCancel.setId("actionCancel");
	            actionSignIn.setText(null);
	            actionCancel.setText("Canceled");


	        }});
	    
	    //Controlling the singinButton that can insert the data
	    btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	            actionCancel.setText(null);
	            String username = userNameTextField.getText();
	            String passw = pwBox.getText();
	        	actionSignIn.setId("actionSignIn");
	        	if(userNameTextField.getText().isEmpty()) {//If isn't inserted an UserName
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service Error!", "Please enter your UserName!");
                    return;
                }
	        	
	        	if(pwBox.getText().isEmpty()) {//If isn't inserted a Password
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Service Error!", "Please enter your Password!");
                    return;
                }
	        	else {
	        		CredentialsBean data = new CredentialsBean();
	        		LoginController controller = new LoginController();
	        		data.setUsername(username);
	        		data.setPassw(passw);
	        		controller.login(data);
	        		if((data.getUser()) != null) {
		        		actionSignIn.setText("Signed in, welcome back " + username);

	        		}
	        		else {
		        		actionSignIn.setText("Sorry username or password is wrong! ");

	        		}
	        		


	        		
	        		
	        	}

	        }});
	    
	    
	    
    
    }
    //A class that will control the errors
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

