package logic.dao;

import java.lang.reflect.InvocationTargetException;

public class DAOFactoryUser extends DAOFactory {
	
//	Singleton Factory
	
	private static DAOFactoryUser ref = null;
	
	public static DAOFactoryUser getReference() {
		
		if (ref == null) {
			ref = new DAOFactoryUser();
		}
		return ref;
	}
	
	private String readDBType() {
		
//		TODO stub
		
		return "Json";
	}
	
	@Override
	public DAOUser getDAOReference() {
		
//		@ return : reference to DAOUSer object
		
		try {
			return (logic.dao.DAOUser) Class.forName(this.getClass().getPackage().getName() + "." + "DAOUser" + this.readDBType()).getMethod("getReference", (Class<?>) null).invoke((Object) null, (Object[])null);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block, must handle these exceptions properly
			
			e.printStackTrace();
			return null;
		}
	}
}
