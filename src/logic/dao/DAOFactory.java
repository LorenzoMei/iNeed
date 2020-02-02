package logic.dao;

import java.lang.reflect.InvocationTargetException;

public abstract class DAOFactory {
	
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
				e.printStackTrace();
		}
		
		return actualFactory;
		}
	
	public abstract Object getDAOReference();
	
//	NOTA : al richiedente della DAO è lasciata la responsabilità del casting alla DAO voluta

}
