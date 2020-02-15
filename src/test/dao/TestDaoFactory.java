package test.dao;

import logic.dao.*;
import org.junit.Assert;
import org.junit.Test;

public class TestDaoFactory {
	
	@Test
	public void testGetDaoReference() {
		
		DAOSupportedEntities entity = DAOSupportedEntities.USER;
		String className = DAOUserSerialize.class.getName();
		
		DAOUser daoRef = (DAOUser) DAOFactory.getReference().getDAOReference(entity);
		Assert.assertEquals(className, daoRef.getClass().getName());
	}

}
