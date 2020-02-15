package logic.misc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Calendar;

public class EntityComparator {
	
	public final static String NAME_ENTITYCOMPARATOR_METHODS = "isSortedBy";
	
	public static Boolean IsSortedByTime(Calendar d1, Calendar d2){
		
		return d1.compareTo(d2) >= 0;
	}
	public static Method getIsSortedByMethod(Order order) throws NoSuchIsSortedByMethodException{
		Method[] allMethods = EntityComparator.class.getDeclaredMethods();
		for (int i = 0; i < allMethods.length; i ++) {
			if (Modifier.isPrivate(allMethods[i].getModifiers()) 
					&& allMethods[i].getName().contains(NAME_ENTITYCOMPARATOR_METHODS + order.getValue())
					&& !allMethods[i].isSynthetic()) {
				return allMethods[i];
			}
		}
		throw new NoSuchIsSortedByMethodException(order);
		
	}
}
