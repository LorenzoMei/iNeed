package logic.misc;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityComparator {
	
	public final static String NAME_ENTITYCOMPARATOR_METHODS = "isSortedBy";
	private static Logger logger = Logger.getLogger(EntityComparator.class.getName());
	
	public static Boolean isSortedByTime(Calendar d1, Calendar d2){
		logger.log(Level.INFO, "d1 is: " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(d1.getTime()));
		logger.log(Level.INFO, "d2 is: " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(d2.getTime()));
		logger.log(Level.INFO, "result is: " + (d1.compareTo(d2) >= 0));
		return d1.compareTo(d2) >= 0;
	}
	public static Method getIsSortedByMethod(Order order) throws NoSuchIsSortedByMethodException{
		Method[] allMethods = EntityComparator.class.getDeclaredMethods();
		for (int i = 0; i < allMethods.length; i ++) {
			if (allMethods[i].getName().contains(NAME_ENTITYCOMPARATOR_METHODS + order.getValue())
					&& !allMethods[i].isSynthetic()) {
				return allMethods[i];
			}
		}
		throw new NoSuchIsSortedByMethodException(order);
		
	}
}
