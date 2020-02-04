package logic.dao;

import logic.entity.User;

public interface DAOUser {
	public User loadUser(User user, String username) throws UserNotFoundException;
	public void storeUser(User user);
}
