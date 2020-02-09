package test.dao;

import logic.dao.*;
import org.junit.Assert;
import org.junit.Test;
import logic.entity.User;

public class TestDAOUser {
	
	@Test
	public void testStoreAndLoadUser() throws UserNotFoundException {
		
		User stored = new User();
		User loaded = new User();
		stored.setUsername("Daniele");
		stored.setPassw("galeone879");
		
		DAOUser dao = (DAOUser) DAOFactory.getReference("User").getDAOReference();
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(stored.getUsername(), loaded.getUsername());
	}
	
	@Test
	public void testStoreAndLoadUserFriend() throws UserNotFoundException {
		User stored = new User();
		User loaded = new User();
		User friend = new User();
		
		friend.setUsername("Pippo");
		
		stored.setUsername("Daniele");
		stored.setPassw("galeone879");
		stored.setFriend(friend);
		
		DAOUser dao = (DAOUser) DAOFactory.getReference("User").getDAOReference();
		dao.storeUser(friend);
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(friend.getUsername(), loaded.getFriend().getUsername());
	}
	
	@Test
	public void testStoreAndLoadUserBirthdate() {
		
	}
}
