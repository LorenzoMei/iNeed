package test.publishanad;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import logic.entity.Ad;
import logic.entity.User;
import logic.publishanad.PublishARequestAdController;
import logic.publishanad.PublishAnAdInterface;
import logic.publishanad.PublishAnAdBean;

class TestPublishARequestAdController {

	@Test
	public void testPublishARequestAd() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		User user = new User();
		user.setUsername("Pippo");
		user.setPassw("Pluto123");
		user.setEmail("cioa@gmaai.co");
		user.setCity("Roma");
		
		String title = "Cerco posto letto";
		String body = "Cerco qualcuno disponibile ad offrire un posto letto per il 17/02/2020";
		
		PublishAnAdBean publishAdBean = new PublishAnAdBean();
		publishAdBean.setUser(user);
		publishAdBean.setTitle(title);
		publishAdBean.setBody(body);
		
		PublishAnAdInterface controller = PublishARequestAdController.getInstance();
		Ad ad = controller.createAd(publishAdBean);
		
		Assert.assertSame(publishAdBean.getUser(), ad.getUser());
		Assert.assertEquals(publishAdBean.getTitle(), ad.getTitle());
		Assert.assertEquals(publishAdBean.getBody(), ad.getBody());
		Assert.assertEquals("Request", ad.getType());
	}

}
