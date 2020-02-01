package logic.dao;

import logic.entity.User;

public interface DAOUser {
	public User loadUser(String username, String passw);
	public void storeUser(User user);
}
