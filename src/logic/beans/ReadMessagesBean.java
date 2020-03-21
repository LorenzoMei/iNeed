package logic.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Message;

public class ReadMessagesBean {
private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private String userSenderUsername;
	private String userReceiverUsername;
	private List<Message> allMessages = null;
	
	public String getMessageInfo(int i, MessageInfos what) {
		String info = null;
		try {
			info = (String) Message.class.getMethod(what.getGetterName(), (Class<?>[]) null).invoke(this.allMessages.get(i), (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			String message = String.format("{0} : {1}", e, e.getMessage());
			logger.log(Level.SEVERE, message);
		}
		return info;
	}
	
	public Date getMessageDate(int i) {
		return allMessages.get(i).getData();
	}
	
	public Object getImage(int i) {
		return allMessages.get(i).getImage();
	}
	
	public void setUserSenderUsername(String userSender) {
		this.userSenderUsername = userSender;
	}
	
	public void setUserReceiverUsername(String userReciver) {
		this.userReceiverUsername = userReciver;
	}
	
	public void setAllMessages(List<Message> allMessages) {
		this.allMessages = allMessages;
	}
	
	public String getUserSenderUsername() {
		return this.userSenderUsername;
	}
	
	public String getUserReceiverUsername() {
		return this.userReceiverUsername;
	}
	
	public int getNumberOfMessages() {
		if (this.allMessages == null) {
			return 0;
		}
		else {
			return allMessages.size();
		}
	}
}
