package logic.view.signup;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class ViewSignUp extends Application {
    
    public static void main(String[] args) {
        Application.launch(ViewSignUp.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml_signup.fxml"));
        
        stage.setTitle("iNeed");
        stage.setScene(new Scene(root, 1100, 875));


        stage.show();
    }
}
