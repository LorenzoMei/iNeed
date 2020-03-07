package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Message;

public class DAOMessageSerialize extends DAOSerialize implements DAOMessage{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOMessageSerialize ref = null;
	
	public static DAOMessageSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOMessageSerialize();
		}
		
		return ref;
	}
	
	private DAOMessageSerialize() {}
	
	public void loadMessage(String sender, String receiver, List<Message> messagesList) throws MessageNotFoundException {
		
		List <String> primaryKeyValues = new ArrayList <>();
		primaryKeyValues.add(sender);
		primaryKeyValues.add(receiver);
		
		File folder = new File(this.readDBPath() + Message.class.getSimpleName());
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
//			p
			logger.log(Level.INFO, sender + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR + receiver);
		  if (listOfFiles[i].getName().contains(sender + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR + receiver)) {
			  primaryKeyValues.add(listOfFiles[i].getName().substring(sender.length() + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length() + receiver.length() + DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - DAOSerialize.SERIALIZED_EXTENSION.length() - DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR.length()));
			  
			  Message newMessage = new Message();
			  try {
				  this.load(newMessage, primaryKeyValues);
			  } catch (ElementInDBNotFoundException e) {
				  logger.log(Level.SEVERE, "file" + e.getPath() + " not found");
			  	} 
			  catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
					logger.log(Level.SEVERE, e.toString());
			  } 
			  
			  messagesList.add(newMessage);
			  primaryKeyValues.remove(listOfFiles[i].getName().substring(sender.length() + PRIMARY_KEY_VALUES_SEPARATOR.length() + receiver.length() + PRIMARY_KEY_VALUES_SEPARATOR.length(), listOfFiles[i].getName().length() - SERIALIZED_EXTENSION.length() - PRIMARY_KEY_VALUES_SEPARATOR.length()));
		  }
		}
	}
	
	public void storeMessage(Message message) {
		
		List<String> primaryKeyNames = new ArrayList<>();
		primaryKeyNames.add("userSenderUsername");
		primaryKeyNames.add("userReceiverUsername");
		primaryKeyNames.add("data");
		
		try {
			this.store(message, primaryKeyNames);
			
		} catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}
}
