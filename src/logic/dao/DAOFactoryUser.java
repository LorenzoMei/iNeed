package logic.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactoryUser implements DAOFactory {
	
//	Singleton Factory
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	
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
	public DAOJson getDAOReference(DAOJson wrapped) {
		
		/*	Decorates wrapped DAOJson object with a DAOUserJson decorator
		 */		
		//	@ return : reference to DAOUSer object
		
		
		try {
			String className = this.getClass().getPackage().getName() + "." + "DAOUser" + this.readDBType();
			if (wrapped == null) {
				wrapped = DBMSJson.getReference();
			}
			DAOJson dao = (DAOJson) Class.forName(className).newInstance();
			DAOJsonDecorator daoDecorator = (DAOJsonDecorator) dao;
			daoDecorator.setDBMS(wrapped);
			return dao;

		} 
		catch (IllegalAccessException | IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException e) {
			// TODO Auto-generated catch block, must handle these exceptions properly
			
			logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
}
