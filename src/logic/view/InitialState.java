package logic.view;

public class InitialState extends View{

	public static final String INITIAL_STATE_TARGET = "logic.view.ViewSignUp";
	
	@Override
	public void goNext(String viewName) {
//		View nextView = new ViewSignUp();
//		p
		System.out.println("State: " + this.getClass().getSimpleName());
		View nextView;
		try {
			nextView = (View) Class.forName(viewName).newInstance();
			nextView.setPrevious(this);
			Context.getReference().setCurrentView(nextView);
			System.out.println("InitialState : nextView set to " + nextView.getClass().getSimpleName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
//		p
	}
}
