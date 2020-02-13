package logic.view;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InitialState extends View{

	public static final String INITIAL_STATE_TARGET = "logic.view.ViewSignUp";
	 Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void goNext(String viewName) {
		View nextView;
		try {
			nextView = (View) Class.forName(viewName).newInstance();
			nextView.setPrevious(this);
			Context.getReference().setCurrentView(nextView);
			String status = "InitialState : nextView set to " + nextView.getClass().getSimpleName();
			logger.log(Level.SEVERE, status);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			logger.log(Level.INFO, e.toString());;
		}
	}
}
