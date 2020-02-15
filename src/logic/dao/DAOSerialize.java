package logic.dao;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.misc.ReflectionMiscellaneous;
import logic.misc.NoSuchGetterException;
import logic.misc.NoSuchSetterException;

public abstract class DAOSerialize {

	Logger logger = Logger.getLogger(this.getClass().getName());
	private String dBPath = readDBPath();
	
	protected final static String PRIMARY_KEY_VALUES_SEPARATOR = "#";
	protected final static String SERIALIZED_EXTENSION = ".ser";
	
	protected String readDBPath() {
//		TODO: stub
		return "db" + File.separator + "serialized" + File.separator;
	}
	
	private void buildAttributes(Class<?> currentClass, List<Field> attributes){
		Class<?> superClass = (Class<?>) currentClass.getGenericSuperclass();		
		Field [] currentAttributes = currentClass.getDeclaredFields();
		Collections.addAll(attributes, currentAttributes);
		if (superClass != Object.class) {
			this.buildAttributes(superClass, attributes);			
		}
	}
	
	private String getPathToLoadFrom(Object obj, List <String> primaryKeyValues) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			stringBuilder.append(primaryKeyValues.get(k));
			stringBuilder.append(DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR);
		}
		stringBuilder.append(DAOSerialize.SERIALIZED_EXTENSION);
		return stringBuilder.toString();
	}

	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		File test = new File(stringBuilder.toString());
		if (!test.exists()) {
			test.mkdirs();
		}
		try{
			for (int k = 0; k < primaryKeyNames.size(); k ++) {
				stringBuilder.append(ReflectionMiscellaneous.getGetter(primaryKeyNames.get(k), obj).invoke(obj, (Object[])null).toString());
				stringBuilder.append(DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR);
			}	
		}
		catch(NoSuchGetterException e) {
			logger.log(Level.SEVERE, "No such get method for attribute " + e.getAttrName());
		}
		stringBuilder.append(DAOSerialize.SERIALIZED_EXTENSION);
		return stringBuilder.toString();
	}
	
	private EntitySerializable entityToSerializable(Object obj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		EntitySerializable buffer = new EntitySerializable();
		buffer.setEntityClassName(obj.getClass().getName());
		List<Field> attributesList = new ArrayList<>();
		this.buildAttributes(obj.getClass(), attributesList);
		
		Field[] attributes = new Field[attributesList.size()];
		attributesList.toArray(attributes);		
		
		for (int i = 0; i < attributes.length; i ++) {

			if ((Modifier.isPrivate(attributes[i].getModifiers()) || Modifier.isProtected(attributes[i].getModifiers()))
					&& !attributes[i].isSynthetic()) {					
				Object attrVal = null;
				try (ObjectOutputStream tester = new ObjectOutputStream(new ByteArrayOutputStream())) {
					attrVal = ReflectionMiscellaneous.getGetter(attributes[i].getName(), obj).invoke(obj, (Object[]) null);
					tester.writeObject(attrVal);
				} catch (NotSerializableException e) {			
					attrVal = this.entityToSerializable(attrVal);
				}
				catch(NoSuchGetterException e) {
					logger.log(Level.SEVERE, "No such get method for attribute " + e.getAttrName());
				}
				finally {
					logger.log(Level.INFO, "adding to buffer: "+attributes[i].getName()+"="+attrVal);
					buffer.getAttributes().put(attributes[i].getName(), (Serializable) attrVal);
				}
			}
		}
		return buffer;
	}
	
	private void serializableToEntity(Object obj, EntitySerializable buffer) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
		
		List<Field> attributesList = new ArrayList<>();
		this.buildAttributes(obj.getClass(), attributesList);	
		Field[] attributes = new Field[attributesList.size()];
		attributes = attributesList.toArray(attributes);
		
		Object buffVal = null;
		Object nestedObj = null;
		
		for (int j = 0; j < attributes.length; j ++) {
				buffVal = buffer.getAttributes().get(attributes[j].getName());
				if ((Modifier.isPrivate(attributes[j].getModifiers()) || Modifier.isProtected(attributes[j].getModifiers())) && !attributes[j].isSynthetic() && !EntitySerializable.class.isInstance(buffVal)){		
						try{
							ReflectionMiscellaneous.getSetter(attributes[j].getName(), obj).invoke(obj, buffVal);
							logger.log(Level.INFO, "loaded value : " + buffVal);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
				else if ((Modifier.isPrivate(attributes[j].getModifiers()) || Modifier.isProtected(attributes[j].getModifiers())) && EntitySerializable.class.isInstance(buffVal)) {
						nestedObj = Class.forName(((EntitySerializable) buffer.getAttributes().get(attributes[j].getName())).getEntityClassName()).newInstance();
						this.serializableToEntity(nestedObj, (EntitySerializable) buffVal);
						try{
							ReflectionMiscellaneous.getSetter(attributes[j].getName(), obj).invoke(obj, nestedObj);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
		}
	}
	
	protected void store(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
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
			throw new ElementInDBNotFoundException(this.getPathToLoadFrom(obj, primaryKeyValues), e);
		}
	}
}