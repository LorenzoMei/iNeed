package logic.dao;


import java.lang.reflect.InvocationTargetException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactory {
	
//	Singleton Factory
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static DAOFactory ref = null;
	public static final String ENTITYNAME_AD = "Ad";
	public static final String ENTITYNAME_USER = "User";
	public static final String ENTITYNAME_MESSAGE = "Message";
	
	private DAOFactory() {}
	
	public static DAOFactory getReference() {
		
		if (ref == null) {
			ref = new DAOFactory();
		}
		return ref;
	}
	
	private String readDBType() {
		
//		TODO stub
		
		return "Serialize";
	}
	
	public Object getDAOReference(String entity) {
		
//		@ return : reference to DAOUSer object
		
		try {
			return Class.forName(this.getClass().getPackage().getName() + "." + "DAO" + entity + this.readDBType()).getMethod("getReference", (Class<?>[]) null).invoke((Object) null, (Object[])null);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			
			logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
}