package test.publishanad;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import logic.beans.PublishAnAdBean;
import logic.dao.AdNotFoundException;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Ad;
import logic.entity.Ads;
import logic.entity.RequestAd;
import logic.entity.User;
import logic.publishanad.PublishAnAdController;

public class TestPublishAnAdController {

	@Test
	public void testPublishAnAdController() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, AdNotFoundException {
		
		User user = new User();
		user.setUsername("Pippo");
		user.setPassw("Pluto123");
		user.setEmail("cioa@gmaai.co");
		user.setCity("Roma");
		
		String title = "Cerco posto letto";
		String body = "Cerco qualcuno disponibile ad offrire un posto letto per il 17/02/2020";
		
		PublishAnAdBean publishAdBean = new PublishAnAdBean();
		publishAdBean.setOwnerUsername(user.getUsername());
		publishAdBean.setTitle(title);
		publishAdBean.setBody(body);
		publishAdBean.setType(Ads.REQUEST.getName());
		
		PublishAnAdController controller = PublishAnAdController.getInstance();
		int id = controller.createAd(publishAdBean);
		
		Ad ad = new RequestAd();
		
		DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
		daoAd.loadAd(ad, id);
		
		
		Assert.assertEquals(publishAdBean.getOwnerUsername(), ad.getOwnerUsername());
		Assert.assertEquals(publishAdBean.getTitle(), ad.getTitle());
		Assert.assertEquals(publishAdBean.getBody(), ad.getBody());
	}

}
