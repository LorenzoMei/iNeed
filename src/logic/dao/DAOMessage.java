package logic.dao;

import java.util.List;

import logic.entity.Message;

public interface DAOMessage {
	
	public void storeMessage(Message message);
	public void loadMessage(String sender, String reciver, List<Message> messagesList) throws MessageNotFoundException;
}
