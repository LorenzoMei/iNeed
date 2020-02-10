package logic.contactuser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Calendar.Builder;

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
	
	public Message contactUser(ContactUserBean contactBean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Message message = new Message();
		
		Calendar calendar = Calendar.getInstance();
		Builder calendarBuilder = new Calendar.Builder();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		
		calendarBuilder.setDate(year, month, date);
		calendarBuilder.setTimeOfDay(hour, minute, seconds);
		
		Calendar data = calendarBuilder.build();
		
		message.setData(data);
		
		Method[] methodsBean = contactBean.getClass().getMethods();
		Method[] methodsEntity = message.getClass().getMethods();
		
		for(int i = 0; i < methodsBean.length; i++) {
			if(methodsBean[i].getName().contains("get")) {
				for(int j = 0; j < methodsEntity.length; j++) {
					if(methodsEntity[j].getName().contains("set" + methodsBean[i].getName().substring(3, 4).toUpperCase() + methodsBean[i].getName().substring(4))) {
						Object value = methodsBean[i].invoke(contactBean, (Object[]) null);
						methodsEntity[j].invoke(message, (Object) value);
					}
				}
			}
		}
		
		return message;
		
	}
}