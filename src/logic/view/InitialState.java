package logic.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Scene;

public class InitialState extends View{

	public static final String INITIAL_STATE_TARGET = GoNextTargets.VIEW_FLOW.getStateName();
	public static final View nextView = (View) new ViewLogin();
	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void goNext(View nextView) {
		
		nextView.setPrevious(this);
		Context.getReference().setCurrentView(nextView);
		String status = "InitialState : nextView set to " + nextView.getClass().getSimpleName();
		logger.log(Level.INFO, status);
		
	}

	@Override
	public Scene buildScene() {
		return null;
	}
}
