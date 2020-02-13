package logic.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.stage.Window;
import logic.entity.User;

public abstract class View {
    
	 Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static User activeUser;
	private String next;
	
	
	public static User getActiveUser() {
//    	logger.log(Level.INFO, "In View the user in getUser is: " + View.activeUser);
		return View.activeUser;
	}
	
	public static void setactiveUser(User activeUser) {
		View.activeUser = activeUser;
//    	logger.log(Level.INFO, "In View the user is: " + View.activeUser);
//    	logger.log(Level.INFO, "In View the user with getUser form geUser is: " + getActiveUser());

	}
	
	public void setNext(String next) {
		this.next = next;
	}
	

	public String getNext() {
		return this.next;
	}
		
	
    private String fxmlpath;
    
    public String getFXMLPath() {
    	return this.fxmlpath;
    }
    
    public void setFXMLPath(String fxmlpath) {
    	this.fxmlpath = fxmlpath;
    }

	
    private View previous;
	
	public void setPrevious(View previous) {
		this.previous = previous;
	}
	
	public View getPrevious() {
		return this.previous;
	}
	
	 protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
	
		public void goNext(String viewName) {
			View nextView;
			try {

				nextView = (View) Class.forName(viewName).newInstance();
				nextView.setPrevious(this);
				
				Context.getReference().setCurrentView(nextView);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				logger.log(Level.SEVERE, e.toString() + " Error in goNext");

			}

		}
}

