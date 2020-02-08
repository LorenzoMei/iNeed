package logic.view.login;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewLogin extends Application {
    
    public static void main(String[] args) {
        Application.launch(ViewLogin.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml_login.fxml"));
        
        stage.setTitle("iNeed");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
