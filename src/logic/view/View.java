package logic.view;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;


public abstract class View {
    
	 Logger loggerView = Logger.getLogger(this.getClass().getName());
	
	
	protected String profileName;
	private String next;
    private View previous;
    protected GoNextTargets goNextTarget;
    protected View nextView;
	 
	public abstract Scene buildScene();
	
	public  String getProfileName() {
		String printThis = "Hi i'm View and this is the profileName: " + this.profileName;
		loggerView.log(Level.INFO, printThis);
		return this.profileName;
	}
	
	public void setProfileName(String username) {
		this.profileName = username;

	}
	
	public void setNext(String next) {
		this.next = next;
	}
	

	public String getNext() {
		return this.next;
	}
		

	public void setPrevious(View previous) {
		this.previous = previous;
	}
	
	public View getPrevious() {
		return this.previous;
	}
	
	
	
	 public View getNextView() {
		 return this.nextView;
	 }
	 
	 public void setNextView(View nextView) {
		 
		 this.nextView = nextView;
	 }
	 
	 public void goNext() {
		 
		 this.goNext(this.getNextView());

	}
	 
	 public void goNext(View nextState) {
		 nextState.setPrevious(this);
			
			Context.getReference().setCurrentView(nextState);
		}
}



