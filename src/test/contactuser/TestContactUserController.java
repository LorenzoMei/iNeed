package test.contactuser;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.Assert;

import logic.beans.MessageInfos;
import logic.beans.ReadMessagesBean;
import logic.beans.WriteMessageBean;
import logic.contactuser.ContactUserController;
import logic.entity.User;

public class TestContactUserController {

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
		
		WriteMessageBean contactBean = new WriteMessageBean();
		
		contactBean.setUserSenderUsername(userSender.getUsername());
		contactBean.setUserReceiverUsername(userReciver.getUsername());
		contactBean.setText(text);
		
		ContactUserController controller = ContactUserController.getInstance();
		controller.writeMessage(contactBean);
		
		ReadMessagesBean rmBean = new ReadMessagesBean();
		rmBean.setUserReceiverUsername(userReciver.getUsername());
		rmBean.setUserSenderUsername(userSender.getUsername());
		
		controller.readMessages(rmBean);
				
		for(int i = 0; i < rmBean.getNumberOfMessages(); i++) {
//			System.out.println("SENDER: " +message.get(i).getUserSenderUsername());
//			System.out.println("RECIVER: " +message.get(i).getUserReceiverUsername());
//			System.out.println("TEXT: " +message.get(i).getText());
//			System.out.println("DATA: " +message.get(i).getData());
//			Assert.assertEquals(contactBean.getUserSenderUsername(), rmBean.getMessageInfo(i, MessageInfos.SENDER));
//			Assert.assertEquals(contactBean.getUserReceiverUsername(), rmBean.getMessageInfo(i, MessageInfos.RECEIVER));
			Assert.assertEquals(contactBean.getText(), rmBean.getMessageInfo(i, MessageInfos.BODY));
		}
	}

}
