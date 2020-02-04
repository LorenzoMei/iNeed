package test.dao;

import logic.dao.*;
import org.junit.Assert;
import org.junit.Test;
import logic.entity.User;

public class TestDAOUserJson {
	
	@Test
	public void testStoreAndLoadUser() {
		
		User stored = new User();
		User loaded;
		
		stored.setUsername("PippoDuro69");
		stored.setPassw("qualunquemente");
		DAOUser dao = (DAOUser) DAOFactory.getReference("User").getDAOReference();
		dao.storeUser(stored);
		loaded = dao.loadUser(stored.getUsername(), stored.getPassw());
//		Assert.assertNotEquals(null, loaded);
		Assert.assertEquals(stored.getUsername(), loaded.getUsername());
	}
}
