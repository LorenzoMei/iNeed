package logic.contactuser;

import java.lang.reflect.InvocationTargetException;

import logic.entity.Message;

public interface ContactUserInterface {
	
	public Message contactUser(ContactUserBean contactBean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException; 
}
