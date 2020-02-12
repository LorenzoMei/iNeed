package logic.contactuser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import logic.entity.Message;

public interface ContactUserInterface {
	
	public void writeMessage(ContactUserBean contactBean) throws IllegalAccessException, InvocationTargetException;
	public List<Message> readMessages(ContactUserBean contactBean) throws IllegalAccessException, InvocationTargetException;
}
