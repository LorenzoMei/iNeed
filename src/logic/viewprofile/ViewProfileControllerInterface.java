package logic.viewprofile;

import logic.beans.ViewProfileBean;
import logic.dao.UserNotFoundException;

public interface ViewProfileControllerInterface {

	public void loadProfile(ViewProfileBean profile) throws UserNotFoundException;



}
