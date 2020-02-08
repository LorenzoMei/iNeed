package logic.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactoryUser implements DAOFactory {
	
//	Singleton Factory
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	
	private static DAOFactoryUser ref = null;
	
	private DAOFactoryUser() {};
	
	public static DAOFactoryUser getReference() {
		
		if (ref == null) {
			ref = new DAOFactoryUser();
		}
		return ref;
	}
	
	private String readDBType() {
		
//		TODO stub
		
		return "Serialize";
	}
	
	@Override
	public Object getDAOReference() {
		
//		@ return : reference to DAOUSer object
		
		try {
			return Class.forName(this.getClass().getPackage().getName() + "." + "DAOUser" + this.readDBType()).getMethod("getReference", (Class<?>[]) null).invoke((Object) null, (Object[])null);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block, must handle these exceptions properly
			
			logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
}