package logic.view;


import javafx.application.Application;
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
		Client.setStage(primaryStage); 
		Context.getReference().goFirstState();
		
		
	}
	
	public static void main(String[] args) {
    	   Application.launch(args);
    }
	

}
