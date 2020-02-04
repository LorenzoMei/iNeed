package logic.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface DAOFactory {
	
	Logger logger = Logger.getLogger(DAOFactory.class.getName());
	
	public static DAOFactory getReference(String entity) {
			
//		le richieste di istanziazione di una DAOFactory sono rimbalzate a una delle sue figlie tramite reflection.
//		La scelta della figlia da istanziare si basa sulla valutazione di un parametro
		
//		@ param entity : simple name of entity type
//		@ return : reference to actual entity factory
		
		String className = DAOFactory.class.getPackage().getName() + "." + "DAOFactory" + entity;
		DAOFactory actualFactory = null;
		try {
			actualFactory = (DAOFactory) Class.forName(className).getMethod("getReference", (Class<?>[]) null).invoke((Object) null, (Object[]) null);
		}
		catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		
		return actualFactory;
		}
	
	public Object getDAOReference();
	
//	NOTA : al richiedente della DAO è lasciata la responsabilità del casting alla DAO voluta

}
