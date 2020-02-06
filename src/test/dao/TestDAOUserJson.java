package test.dao;

import logic.dao.*;
import org.junit.Assert;
import org.junit.Test;
import logic.entity.User;

public class TestDAOUserJson {
	
	@Test
	public void testStoreAndLoadUser() throws UserNotFoundException {
		
		User stored = new User();
		User loaded = new User();
		
		stored.setUsername("PippoDuro69");
		stored.setPassw("qualunquemente");
		DAOUser dao = (DAOUser) DAOFactory.getReference("User").getDAOReference(null);
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(stored.getUsername(), loaded.getUsername());
	}
}
