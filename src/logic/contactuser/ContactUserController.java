package logic.contactuser;

import java.lang.reflect.InvocationTargetException;

import logic.entity.Message;
import logic.support.Data;
import logic.support.ValueEntity;

public class ContactUserController implements ContactUserInterface{
	
	private static ContactUserController instance = null;
	
	public static ContactUserController getInstance() {
		if(instance == null)
			instance = new ContactUserController();
		return instance;
	}
	
	private ContactUserController() {		
	}
	
	public Message contactUser(ContactUserBean contactBean) throws IllegalAccessException, InvocationTargetException {
		
		Message message = new Message();
		Data data = new Data();
		
		message.setData(data.buildDateAndHour());
		
		ValueEntity setEntity = new ValueEntity();
		setEntity.getFromBeanAndSetEntity(contactBean, message);
		
		return message;
		
	}
}