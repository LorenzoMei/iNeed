package logic.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactoryAd implements DAOFactory {
	
//	Singleton Factory
	Logger logger = Logger.getLogger(DAOFactoryAd.class.getName());
	
	private static DAOFactoryAd ref = null;
	
	private DAOFactoryAd() {};
	
	public static DAOFactoryAd getReference() {
		
		if (ref == null) {
			ref = new DAOFactoryAd();
		}
		return ref;
	}
	
	private String readDBType() {
		
//		TODO stub
		
		return "Serialize";
	}
	
	@Override
	public Object getDAOReference() {
		
//		@ return : reference to DAOAd object
		
		try {
			return Class.forName(this.getClass().getPackage().getName() + "." + "DAOAd" + this.readDBType()).getMethod("getReference", (Class<?>[]) null).invoke((Object) null, (Object[])null);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block, must handle these exceptions properly
			
			logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
}
