package logic.view;

public class InitialState extends View{

	@Override
	public void goNext() {
		View nextView = new ViewSignUp();
		nextView.setPrevious(this);
		Context.getReference().setCurrentView(nextView);
	}
}
