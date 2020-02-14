package logic.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Context {
	Logger logger = Logger.getLogger(this.getClass().getName());


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
	
	public void goFirstState() {
		
		this.goNext(InitialState.INITIAL_STATE_TARGET);

	}
	
	public void goNext(String viewName) {
		if (currentView == null) {
			currentView = new InitialState();
		}
		
		String error = "Context: currentState is " + currentView.getClass().getSimpleName();
		logger.log(Level.SEVERE, error);
		currentView.goNext(viewName);
		
		String error1 = "Context: currentState changed to " + currentView.getClass().getSimpleName();
		logger.log(Level.SEVERE, error1);


		this.draw(Client.getStage());
	}
	
	public Parent loadScreen(String resource) throws FXMLNotFoundException {
       
            try {
				return FXMLLoader.load(getClass().getResource(resource));
			} catch (IOException e) {
				throw new FXMLNotFoundException();				
			}
        

	}
	
	public void draw(Stage stage) {
		
		try {

			String error = "I'M ON CONTEXT MY CURRENT VIEW IS: " + currentView.getClass().getSimpleName();
			String error2 = "I'M ON CONTEXT MY CURRENT VIEW fxmlPath IS: " + currentView.getFXMLPath();
			logger.log(Level.INFO, error);
			logger.log(Level.INFO, error2);

			Parent root = this.loadScreen(currentView.getFXMLPath());
			stage.setScene(new Scene(root));
			stage.show();

			
		} catch (FXMLNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
			
		}
		
	}
	
	public View getCurrentView() {
		return this.currentView;
	}
	
	
	
}
