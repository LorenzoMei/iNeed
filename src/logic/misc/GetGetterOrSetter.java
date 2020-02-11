package logic.misc;

import java.lang.reflect.Method;

public class GetGetterOrSetter {
	
	public static Method getGetter(String attrName, Object obj) throws NoSuchGetterException {
		try{
			return getGetterOrSetter("get", attrName, obj);
		}
		catch(NoSuchMethodException e) {
			throw new NoSuchGetterException(attrName); 
		}
	}
	
	public static Method getSetter(String attrName, Object obj) throws NoSuchSetterException {
		try{
			return getGetterOrSetter("set", attrName, obj);
		}
		catch(NoSuchMethodException e) {
			throw new NoSuchSetterException(attrName);
		}
	}
	
	private static Method getGetterOrSetter(String getOrSet, String attrName, Object obj) throws NoSuchMethodException {
		
		Method[] methods = obj.getClass().getMethods();
		System.out.println("Searching method " + getOrSet + " for: " + attrName);
		for (int j = 0; j < methods.length; j ++) {
			if (methods[j].getName().contains(getOrSet) && methods[j].getName().contains(attrName.substring(0, 1).toUpperCase() + attrName.substring(1)) && !methods[j].isSynthetic()) {
				System.out.println(methods[j].getName());
				return methods[j];
			}
		}
		
		throw new NoSuchMethodException();
	}
}
