package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Context {

	private static Context ref = null;
	
	public static Context getReference() {
		if (Context.ref == null) {
			Context.ref = new Context();
		}
		return Context.ref;
	}
	
	private View currentView;
	
	public void setCurrentView(View currentView) {
		this.currentView = currentView;
	}
	
	public void goNext() {
		
		if (currentView == null) {
			currentView = new InitialState();
		}
		
		currentView.goNext();
		this.draw(Client.getStage());
		
		
	}
	
	public Parent loadScreen(String resource) throws IOException {
       
        	Parent root = FXMLLoader.load(getClass().getResource(resource));
            return root;
        

	}
	
	public void draw(Stage stage) {
		
		try {
			Parent root = this.loadScreen(currentView.getFXMLPath());
			stage.setScene(new Scene(root));
			stage.show();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public View getCurrentView() {
		return this.currentView;
	}
	
	
	
}
