package logic.dao;

import logic.entity.User;

public interface DAOUser {
	public void loadUser(User user, String username) throws UserNotFoundException;
	public void storeUser(User user);
}
