package logic.dao;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public abstract class DAOSerialize {
	
	private String dBPath = readDBPath();
	
	private String readDBPath() {
//		TODO: stub
		return "db" + File.separator + "serialized" + File.separator;
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
	
	private String getPathToLoadFrom(Object obj, List <String> primaryKeyValues) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			stringBuilder.append(primaryKeyValues.get(k));
		}
		stringBuilder.append(".ser");
		
		return stringBuilder.toString();
	}

	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			this.getGetterOrSetter("get", primaryKeyNames.get(k), obj).invoke(obj, (Object[])null);
		}
		stringBuilder.append(".ser");
		
		return stringBuilder.toString();
	}
	
	private EntitySerializable entityToSerializable(Object obj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		
		EntitySerializable buffer = new EntitySerializable();
		buffer.setEntityClassName(obj.getClass().getName());
		Field[] attributes = obj.getClass().getDeclaredFields();
		for (int i = 0; i < attributes.length; i ++) {
			if (Modifier.isPrivate(attributes[i].getModifiers()) && !attributes[i].isSynthetic()) {					
				Object attrVal = this.getGetterOrSetter("get", attributes[i].getName(), obj).invoke(obj, (Object[]) null);
				try (ObjectOutputStream tester = new ObjectOutputStream(new ByteArrayOutputStream())) {
					tester.writeObject(attrVal);
				} catch (NotSerializableException e) {
					attrVal = this.entityToSerializable(attrVal);
				}
				finally {
					buffer.getAttributes().put(attributes[i].getName(), attrVal);
				}
			}
		}
		return buffer;
	}
	
	private void serializableToEntity(Object obj, EntitySerializable buffer) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
		Field[] attributes = obj.getClass().getDeclaredFields();
		Object buffVal = null;
		Object nestedObj = null;
		for (int j = 0; j < attributes.length; j ++) {
				buffVal = buffer.getAttributes().get(attributes[j].getName());
				if (Modifier.isPrivate(attributes[j].getModifiers()) && !attributes[j].isSynthetic() && !EntitySerializable.class.isInstance(buffVal)){		
						this.getGetterOrSetter("set", attributes[j].getName(), obj).invoke(obj, buffVal);
				}
				else if (Modifier.isPrivate(attributes[j].getModifiers()) && !attributes[j].isSynthetic() && EntitySerializable.class.isInstance(buffVal)) {
						nestedObj = Class.forName(((EntitySerializable) buffer.getAttributes().get(attributes[j].getName())).getEntityClassName()).newInstance();
						this.serializableToEntity(nestedObj, (EntitySerializable) buffVal);
						this.getGetterOrSetter("set", attributes[j].getName(), obj).invoke(obj, nestedObj);
					}
				}
		}
	
	protected void store(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		
		try (
				FileOutputStream dest = new FileOutputStream(this.getPathToStoreIn(obj, primaryKeyNames));
				ObjectOutputStream writer = new ObjectOutputStream(dest);){
			EntitySerializable buffer = this.entityToSerializable(obj);
			writer.writeObject(buffer);
		}
	}
	protected void load(Object obj, List <String> primaryKeyValues) throws IOException, ClassNotFoundException, ElementInDBNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		try (
				FileInputStream src = new FileInputStream(this.getPathToLoadFrom(obj, primaryKeyValues));
				ObjectInputStream reader = new ObjectInputStream(src);){
			EntitySerializable buffer = (EntitySerializable) reader.readObject();
			this.serializableToEntity(obj, buffer);
		}
		catch (FileNotFoundException e) {
			throw new ElementInDBNotFoundException(this.getPathToLoadFrom(obj, primaryKeyValues));
		}
	}
	
}
