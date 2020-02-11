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
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.misc.GetGetterOrSetter;
import logic.misc.NoSuchGetterException;
import logic.misc.NoSuchSetterException;

public abstract class DAOSerialize {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private String dBPath = readDBPath();
	
	private String readDBPath() {
//		TODO: stub
		return "db" + File.separator + "serialized" + File.separator;
	}
	
	private void buildAttributes(Class<?> currentClass, List<Field> attributes){
		
		Class<?> superClass = (Class<?>) currentClass.getGenericSuperclass();
		
		System.out.println("current Class " + currentClass.getSimpleName() + " has " + superClass.getSimpleName() + " as super");
		
		Field [] currentAttributes = currentClass.getDeclaredFields();
		for (int i = 0; i < currentAttributes.length; i++) {
			System.out.println("attributes += " + currentAttributes[i].getName());
			attributes.add(currentAttributes[i]);
		}
		if (superClass != Object.class) {
			this.buildAttributes(superClass, attributes);			
		}
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

	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalArgumentException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		File test = new File(stringBuilder.toString());
		if (!test.exists()) {
			test.mkdirs();
		}
		try{
			for (int k = 0; k < primaryKeyNames.size(); k ++) {
				stringBuilder.append(GetGetterOrSetter.getGetter(primaryKeyNames.get(k), obj).invoke(obj, (Object[])null));
			}	
		}
		catch(NoSuchGetterException e) {
			logger.log(Level.SEVERE, "No such get method for attribute " + e.getAttrName());
		}
		stringBuilder.append(".ser");
		return stringBuilder.toString();
	}
	
	private EntitySerializable entityToSerializable(Object obj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, IllegalArgumentException{
		EntitySerializable buffer = new EntitySerializable();
		buffer.setEntityClassName(obj.getClass().getName());
		List<Field> attributesList = new ArrayList<>();
		this.buildAttributes(obj.getClass(), attributesList);
		
		
		Field[] attributes = new Field[attributesList.size()];
		attributesList.toArray(attributes);
		
		
		
		System.out.println("TENTO DI SERIALIZZARE OBJECT: " + obj.getClass().getName());
		
		
		
		for (int i = 0; i < attributes.length; i ++) {

			if ((Modifier.isPrivate(attributes[i].getModifiers()) || Modifier.isProtected(attributes[i].getModifiers())) && !attributes[i].isSynthetic()) {					
				System.out.println("NOME ATTRIBUTO: " + attributes[i].getName());
				Object attrVal = null;
				try (ObjectOutputStream tester = new ObjectOutputStream(new ByteArrayOutputStream())) {
					attrVal = GetGetterOrSetter.getGetter(attributes[i].getName(), obj).invoke(obj, (Object[]) null);
					tester.writeObject(attrVal);
				} catch (NotSerializableException e) {			
					System.out.println("NON SERIALIZZABILE");
					attrVal = this.entityToSerializable(attrVal);
				}
				catch(NoSuchGetterException e) {
					logger.log(Level.SEVERE, "No such get method for attribute " + e.getAttrName());
				}
				finally {
					buffer.getAttributes().put(attributes[i].getName(), (Serializable) attrVal);
					System.out.println("AGGIUNTO A BUFFER: " + attributes[i].getName() + ", " + attrVal);
				}
			}
		}
		return buffer;
	}
	
	private void serializableToEntity(Object obj, EntitySerializable buffer) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
		
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
							GetGetterOrSetter.getSetter(attributes[j].getName(), obj).invoke(obj, buffVal);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
				else if ((Modifier.isPrivate(attributes[j].getModifiers()) || Modifier.isProtected(attributes[j].getModifiers())) && EntitySerializable.class.isInstance(buffVal)) {
						nestedObj = Class.forName(((EntitySerializable) buffer.getAttributes().get(attributes[j].getName())).getEntityClassName()).newInstance();
						this.serializableToEntity(nestedObj, (EntitySerializable) buffVal);
						try{
							GetGetterOrSetter.getSetter(attributes[j].getName(), obj).invoke(obj, nestedObj);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
		}
	}
	
	protected void store(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, IllegalArgumentException{
		try (
				FileOutputStream dest = new FileOutputStream(this.getPathToStoreIn(obj, primaryKeyNames));
				ObjectOutputStream writer = new ObjectOutputStream(dest);){
			EntitySerializable buffer = this.entityToSerializable(obj);
			writer.writeObject(buffer);
		}
	}
	protected void load(Object obj, List <String> primaryKeyValues) throws IOException, ClassNotFoundException, ElementInDBNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalArgumentException {
		
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