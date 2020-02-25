package test.contactuser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import logic.contactuser.ContactUserBean;
import logic.contactuser.ContactUserController;
import logic.contactuser.ContactUserInterface;
import logic.entity.Message;
import logic.entity.User;

class TestContactUserController {

	@Test
	public void testMessageEntity() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		User userSender = new User();
		userSender.setName("Pippo");
		userSender.setUsername("Pluto");
		userSender.setEmail("ciao@gmail.com");
		userSender.setCity("Rome");
		
		User userReciver = new User();
		userReciver.setName("topolino");
		userReciver.setUsername("minnie");
		userReciver.setEmail("deve@njdevde.com");
		userReciver.setCity("Torino");
		
		String text = "Ciao, ti contatto per metterci d'accordo sul favore che ti dovevo fare";
		
		ContactUserBean contactBean = new ContactUserBean();
		
		contactBean.setUserSenderUsername(userSender.getUsername());
		contactBean.setUserReciverUsername(userReciver.getUsername());
		contactBean.setText(text);
		
		ContactUserInterface controller = ContactUserController.getInstance();
		controller.writeMessage(contactBean);
		
		List<Message> message = controller.readMessages(contactBean);
				
		for(int i = 0; i < message.size(); i++) {
			System.out.println("SENDER: " +message.get(i).getUserSenderUsername());
			System.out.println("RECIVER: " +message.get(i).getUserReciverUsername());
			System.out.println("TEXT: " +message.get(i).getText());
			System.out.println("DATA: " +message.get(i).getData());
			Assert.assertEquals(contactBean.getUserSenderUsername(), message.get(i).getUserSenderUsername());
			Assert.assertEquals(contactBean.getUserReciverUsername(), message.get(i).getUserReciverUsername());
			Assert.assertEquals(contactBean.getText(), message.get(i).getText());
		}
	}

}
