package logic.dao;

import java.lang.reflect.InvocationTargetException;

public class DAOFactory {
	
	private static DAOFactory reference = null;
	
	public static DAOFactory getReference() {
		
		if (reference == null) {
			reference = new DAOFactory();
		}
		
		return reference;
		
	}
	
	public DAO getDAOReference() {
		/* After reading a system property, chooses the subclass of DAO by reflection using the following assumptions:
		 * 1. All DAO subclasses are named like DAOdbType,
		 * 2. DAOFactory, DAO and all DAO subclasses are located in the same package.
		 * 
		 * @ return DAO, null if there was some error
		*/ 
		
		try {
			return (DAO) Class.forName("DAO" + readDBType()).getMethod("getReference", (Class<?>) null).invoke((Object) null, (Object[])null);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block, must handle these exceptions properly
			
			e.printStackTrace();
			return null;
		}
	}
	
	private String readDBType() {
		
		// TODO: stub
		
		return "json";
	}

}
