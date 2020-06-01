// Daniele La Prova
package test.dao;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import logic.dao.AdNotFoundException;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Ad;
import logic.entity.RequestAd;
import logic.entity.User;

public class TestDaoAd {

	@Test
	public void testStoreAndLoadAdUser() throws AdNotFoundException {
		
		
		User user = new User();
		user.setUsername("Pippo");
		user.setPassw("Pluto123");
		user.setEmail("cioa@gmaai.co");
		user.setCity("Roma");
		
		String title = "Cerco posto letto";
		String body = "Cerco qualcuno disponibile ad offrire un posto letto per il 17/02/2020";

		Ad storedAd = new RequestAd();
		storedAd.setOwnerUsername(user.getUsername());
		storedAd.setTitle(title);
		storedAd.setBody(body);
		storedAd.setData(Calendar.getInstance());
		
		DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
		
		int storedId = daoAd.storeNewAd(storedAd);
		Ad loadedAd = new RequestAd();
		daoAd.loadAd(loadedAd, storedId);
		
		Assert.assertEquals(storedAd.getOwnerUsername(), loadedAd.getOwnerUsername());
	}
}
