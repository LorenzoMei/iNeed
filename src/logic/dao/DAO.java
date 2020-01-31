package logic.dao;

import logic.entity.*;

public interface DAO {
	
	public User loadUser(String username, String passwd);
	public void storeUser(User user);

}
