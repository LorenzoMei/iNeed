package logic.contactuser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import logic.entity.Data;
import logic.entity.Message;

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
		
		Method[] methodsBean = contactBean.getClass().getMethods();
		Method[] methodsEntity = message.getClass().getMethods();
		
		for(int i = 0; i < methodsBean.length; i++) {
			if(methodsBean[i].getName().contains("get")) {
				for(int j = 0; j < methodsEntity.length; j++) {
					if(methodsEntity[j].getName().contains("set" + methodsBean[i].getName().substring(3, 4).toUpperCase() + methodsBean[i].getName().substring(4))) {
						Object value = methodsBean[i].invoke(contactBean, (Object[]) null);
						methodsEntity[j].invoke(message, value);
					}
				}
			}
		}
		
		return message;
		
	}
}