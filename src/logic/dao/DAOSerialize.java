package logic.dao;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public abstract class DAOSerialize {
	
	private String DBPath = readDBPath();
	
	private String readDBPath() {
//		TODO: stub
		return "db/serialized/";
	}
	
	protected Method getGetterOrSetter(String getOrSet, String attrName, Object obj) throws NoSuchMethodException {
		
		Method[] methods = obj.getClass().getMethods();
		for (int j = 0; j < methods.length; j ++) {
			if (methods[j].getName().contains(getOrSet) && methods[j].getName().contains(attrName.substring(0, 1).toUpperCase() + attrName.substring(1)) && !methods[j].isSynthetic()) {
				
				return methods[j];
			}
		}
		throw new NoSuchMethodException();
	}
	
	private String getPathToLoadFrom(List <String> primaryKeyValues) {
		String path = DBPath;
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			path += primaryKeyValues.get(k);
		}
		path += ".ser";
		
		return path;
	}
	
	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		String path = DBPath;
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			
			path += this.getGetterOrSetter("get", primaryKeyNames.get(k), obj).invoke(obj, (Object[])null); 
		}
		path += ".ser";
		
		return path;
	}
	protected void store(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, IOException{

		EntitySerializable buffer = new EntitySerializable();
		try (
				FileOutputStream dest = new FileOutputStream(this.getPathToStoreIn(obj, primaryKeyNames));
				ObjectOutputStream writer = new ObjectOutputStream(dest);){
			Field[] attributes = obj.getClass().getDeclaredFields();
			
			for (int i = 0; i < attributes.length; i ++) {
				if (Modifier.isPrivate(attributes[i].getModifiers()) && !attributes[i].isSynthetic()) {
										
					buffer.getAttributes().put(attributes[i].getName(), this.getGetterOrSetter("get", attributes[i].getName(), obj).invoke(obj, (Object[]) null));
				}
			}
			writer.writeObject(buffer);
		}
	}
	protected void load(Object obj, List <String> primaryKeyNames, List <String> primaryKeyValues) throws IOException, ClassNotFoundException, ElementInDBNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		try (
				FileInputStream src = new FileInputStream(this.getPathToLoadFrom(primaryKeyValues));
				ObjectInputStream reader = new ObjectInputStream(src);){
			EntitySerializable buffer = (EntitySerializable) reader.readObject();
			Field[] attributes = obj.getClass().getDeclaredFields();
			for (int j = 0; j < attributes.length; j ++) {
				if (Modifier.isPrivate(attributes[j].getModifiers()) && !attributes[j].isSynthetic()) {
					
					this.getGetterOrSetter("set", attributes[j].getName(), obj).invoke(obj, buffer.getAttributes().get(attributes[j].getName()));
				}				
			}
		}
		catch (FileNotFoundException e) {
			throw new ElementInDBNotFoundException(this.getPathToLoadFrom(primaryKeyValues));
		}
	}
	
}
