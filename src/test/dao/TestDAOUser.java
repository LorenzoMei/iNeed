// Daniele La Prova
package test.dao;

import logic.dao.*;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import logic.entity.User;

public class TestDAOUser {
	
	@Test
	public void testStoreAndLoadUser() throws UserNotFoundException {
		
		User stored = new User();
		User loaded = new User();
		stored.setUsername("amedeo");
		stored.setPassw("qwerty");
		stored.setName("Nome");
		stored.setSurname("Cognome");
		stored.setCity("roma");
		stored.setEmail("a@b.com");
		stored.setBDate(Calendar.getInstance());
		
		DAOUser dao = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(stored.getUsername(), loaded.getUsername());
	}
}
