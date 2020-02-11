package test.contactuser;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

import logic.contactuser.ContactUserBean;
import logic.contactuser.ContactUserController;
import logic.contactuser.ContactUserInterface;
import logic.entity.Message;
import logic.entity.User;

class TestContactUserController {

	@Test
	public void testMessageEntity() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		User user = new User();
		user.setName("Pippo");
		user.setSurName("Pluto");
		user.setEmail("ciao@gmail.com");
		user.setCity("Rome");
		
		String text = "Ciao, ti contatto per metterci d'accordo sul favore che ti dovevo fare";
		
		ContactUserBean bean = new ContactUserBean();
		
		bean.setUser(user);
		bean.setText(text);
		
		ContactUserInterface controller = ContactUserController.getInstance();
		Message message = controller.contactUser(bean);
		
		Assert.assertSame(bean.getUser(), message.getUser());
		Assert.assertEquals(bean.getText(), message.getText());
		System.out.println(message.getData().getTime());
	}

}
