package logic.dao;

import logic.entity.User;

public interface DAOUser {
	public User loadUser(User user);
	public void storeUser(User user);
}
