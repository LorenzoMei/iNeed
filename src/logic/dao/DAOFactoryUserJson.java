package logic.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactoryUserJson implements DAOFactory {
	
//	Singleton Factory
	Logger logger = Logger.getLogger(DAOUserJson.class.getName());
	
	private static DAOFactoryUserJson ref = null;
	
	public static DAOFactoryUserJson getReference() {
		
		if (ref == null) {
			ref = new DAOFactoryUserJson();
		}
		return ref;
	}
	
	@Override
	public DAOJson getDAOReference(DAOJson wrapped) {
		
		/*	Decorates wrapped DAOJson object with a DAOUserJson decorator
		 */		
		//	@ return : reference to DAOUSer object
		
		
		try {
			String className = this.getClass().getPackage().getName() + "." + "DAOUserJson";
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
