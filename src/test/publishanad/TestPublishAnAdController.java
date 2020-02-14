package test.publishanad;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import logic.beans.PublishAnAdBean;
import logic.entity.Ad;
import logic.entity.User;
import logic.publishanad.PublishAnAdInterface;
import logic.publishanad.PublishAnAdController;

class TestPublishAnAdController {

	@Test
	public void testPublishAnAdController() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		User user = new User();
		user.setUsername("Pippo");
		user.setPassw("Pluto123");
		user.setEmail("cioa@gmaai.co");
		user.setCity("Roma");
		
		String title = "Cerco posto letto";
		String body = "Cerco qualcuno disponibile ad offrire un posto letto per il 17/02/2020";
		
		PublishAnAdBean publishAdBean = new PublishAnAdBean();
		publishAdBean.setUsername(user.getUsername());
		publishAdBean.setTitle(title);
		publishAdBean.setBody(body);
		publishAdBean.setType("Richiesta");
		
		PublishAnAdInterface controller = PublishAnAdController.getInstance();
		Ad ad = controller.createAd(publishAdBean);
		
		Assert.assertSame(publishAdBean.getUsername(), ad.getOwnerUsername());
		Assert.assertEquals(publishAdBean.getTitle(), ad.getTitle());
		Assert.assertEquals(publishAdBean.getBody(), ad.getBody());
	}

}
