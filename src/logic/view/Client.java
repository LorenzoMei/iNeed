package logic.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{

	private static Stage stage;	
	
	public static Stage getStage() {
		return Client.stage; 
	}
	
	public static void setStage(Stage stage) {
		Client.stage = stage;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Client.stage = primaryStage;
		Context.getReference().goNext();
		
		
	}
	
	public static void main(String[] args) {
    	   Application.launch(args);
    }
	

}
