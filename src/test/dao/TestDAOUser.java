package test.dao;

import logic.dao.*;

import java.util.Calendar;
import java.util.Calendar.Builder;

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
		stored.setName("Danielo");
		stored.setSurname("Le Provi");
		stored.setCity("roma");
		stored.setEmail("a@b.com");
		
		DAOUser dao = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(stored.getUsername(), loaded.getUsername());
	}
	
//	@Test
//	public void testStoreAndLoadUserFriend() throws UserNotFoundException {
//		User stored = new User();
//		User loaded = new User();
//		User friend = new User();
//		
//		friend.setUsername("Pippo");
//		
//		stored.setUsername("Daniele");
//		stored.setPassw("galeone879");
//		
//		
//		Builder builder = new Builder();
//		builder.setDate(1998, 6, 13);
//		Calendar val = builder.build();
//		
//		stored.setBDate(val);
//		
//		stored.setFriend(friend);
//		
//		DAOUser dao = (DAOUser) DAOFactory.getReference().getDAOReference("User");
//		dao.storeUser(friend);
//		dao.storeUser(stored);
//		dao.loadUser(loaded, stored.getUsername());
//		Assert.assertEquals(friend.getUsername(), loaded.getFriend().getUsername());
//	}
	
	@Test
	public void testStoreAndLoadUserBirthdate() throws UserNotFoundException {
		User stored = new User();
		User loaded = new User();
		
		Builder calendarBuilder= new Calendar.Builder();
		calendarBuilder.setDate(1998, 6, 13);
		Calendar storedBDate = calendarBuilder.build();
		
		stored.setUsername("Daniele");
		stored.setPassw("galeone879");
		stored.setBDate(storedBDate);
		
		DAOUser dao = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		dao.storeUser(stored);
		dao.loadUser(loaded, stored.getUsername());
		Assert.assertEquals(stored.getBDate().get(Calendar.YEAR), loaded.getBDate().get(Calendar.YEAR));
		Assert.assertEquals(stored.getBDate().get(Calendar.MONTH), loaded.getBDate().get(Calendar.MONTH));
		Assert.assertEquals(stored.getBDate().get(Calendar.DAY_OF_MONTH), loaded.getBDate().get(Calendar.DAY_OF_MONTH));
	}
}
