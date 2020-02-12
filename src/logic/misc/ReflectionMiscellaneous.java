package logic.misc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionMiscellaneous {
	
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
	
	public static List<Field> getSupported(Object obj, String constantName) {
//		p
		System.out.println("getting all constants of " + obj.getClass().getSimpleName());
		Field[] allFields = obj.getClass().getDeclaredFields();
		List<Field> supported = new ArrayList<>();
			for (int i = 0; i < allFields.length; i ++) {
				if (Modifier.isFinal(allFields[i].getModifiers())
							&& Modifier.isStatic(allFields[i].getModifiers())
							&& !allFields[i].isSynthetic()
							&& allFields[i].getName().contains(constantName)) {
//					p
					System.out.println("adding " + allFields[i].getName());
					supported.add(allFields[i]);
					}
		}
		return supported;
	}
}
