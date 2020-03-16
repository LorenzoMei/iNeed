package logic.view;


import java.util.logging.Level;
import java.util.logging.Logger;
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
	
	public View getCurrentView() {
		return this.currentView;
	}
	
	public void goFirstState() {
		currentView = new InitialState();
		
		currentView.goNext(InitialState.nextView);
//		this.goNext(InitialState.INITIAL_STATE_TARGET);

		this.draw(Client.getStage());

	}
	
	public void goNext() {
		
		String error = "Context: currentState is " + currentView.getClass().getSimpleName();
		logger.log(Level.INFO, error);
		currentView.goNext();
		
		String error1 = "Context: currentState changed to " + currentView.getClass().getSimpleName();
		logger.log(Level.INFO, error1);


		this.draw(Client.getStage());
	}
	
	public void draw(Stage stage) {
		
		stage.setScene(currentView.buildScene());
		String error = "I'M ON CONTEXT MY CURRENT VIEW IS: " + currentView.getClass().getSimpleName();
		logger.log(Level.INFO, error);
		stage.show();


		
	}
	
	
	
	
}
