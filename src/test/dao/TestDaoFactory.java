package test.dao;

import logic.dao.*;
import org.junit.Assert;
import org.junit.Test;

public class TestDaoFactory {
	
	@Test
	public void testGetDaoReference() {
		
		String entity = "User";
		String className = "logic.dao.DAOUserSerialize";
		
		DAOUser daoRef = (DAOUser) DAOFactory.getReference().getDAOReference(entity);
		Assert.assertEquals(className, daoRef.getClass().getName());
	}

}
