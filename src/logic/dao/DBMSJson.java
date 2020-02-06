package logic.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class DBMSJson implements DAOJson{
	
//	Handles operations for manipulating data in JSON files
	
	private static DBMSJson ref = null;
	
	public static DBMSJson getReference() {
		
		if (ref == null) {
			ref = new DBMSJson();
		}
		
		return ref;
	}
	
	private String DBPath = readDBPath();
	
	private String readDBPath() {
//		TODO stub
		
		return "db/json/";
	}
	
	private Boolean checkPrimaryKey(JSONObject node, List<String> primaryKeyNames, List<String> primaryKeyValues) {
		
//		Checks if the node loaded from JSON file has the same primary key values has the ones provided
		
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			if (!node.get(primaryKeyNames.get(k)).equals(primaryKeyValues.get(k))){
				return false;
			}
		}
		return true;
	}
	
	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		
//		Gets the path of the file where obj with primaryKeyNames provided will be stored in
		
		String path = DBPath + obj.getClass().getSimpleName() + "/";
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			path += this.getGetterOrSetter("get", primaryKeyNames.get(k), obj).invoke(obj, (Object[])null);
		}
		path += ".json";
		
		return path;
	}
	
	private String getPathToLoadFrom(Object obj, List <String> primaryKeyValues) {
		
//		Gets the path of the file where obj with primaryKeyValues provided is stored in
		
		String path = DBPath + obj.getClass().getSimpleName() + "/";
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			path += primaryKeyValues.get(k);
		}
		path += ".json";
		
		return path;
	}
	
	private Method getGetterOrSetter(String getOrSet, String attrName, Object obj) throws NoSuchMethodException {
		
//		Gets the getter or setter of attrName attribute of obj object.
//		@ return Method getter or setter if retrevied, raises an exception otherwise
		
		Method[] methods = obj.getClass().getMethods();
		for (int j = 0; j < methods.length; j ++) {
			if (methods[j].getName().contains(getOrSet) && methods[j].getName().contains(attrName.substring(0, 1).toUpperCase() + attrName.substring(1)) && !methods[j].isSynthetic()) {
				return methods[j];
			}
		}
		throw new NoSuchMethodException();
	}
	
	public void store(Object obj, List<String> primaryKeyNames) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		
//		It stores object data in a JSON array, coping values of all Object attributes using reflection
		
		File destDB = new File(this.getPathToStoreIn(obj, primaryKeyNames));
		try (FileWriter writer = new FileWriter(destDB)) {
			JSONObject values = new JSONObject();
			JSONArray root = new JSONArray();
			Field[] attributes = obj.getClass().getDeclaredFields();
						
			for (int i = 0; i < attributes.length; i ++) {
				if (Modifier.isPrivate(attributes[i].getModifiers()) && !attributes[i].isSynthetic()) {
					values.put(attributes[i].getName(), this.getGetterOrSetter("get", attributes[i].getName(), obj).invoke(obj, (Object[]) null));
				}
			}
			root.add(values);
			root.writeJSONString(writer); 
		}

	}
	
	public void load(Object obj, List <String> primaryKeyNames, List <String> primaryKeyValues) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, IOException, ParseException, ElementInDBNotFoundException {
		
//		Searches in every node of the root in DB for a Object with the same primaryKey names and values as the ones provided.
//		@ return Object if retrieved, null otherwise
		
		File srcDB = new File(this.getPathToLoadFrom(obj, primaryKeyValues));
		try (FileReader reader = new FileReader(srcDB)){
			JSONArray root = (JSONArray) JSONValue.parseWithException(reader);
			for (int i = 0; i < root.size(); i ++) {
				JSONObject currentNode = (JSONObject) root.get(i);
				if (this.checkPrimaryKey(currentNode, primaryKeyNames, primaryKeyValues)) {
					Field[] attributes = obj.getClass().getDeclaredFields();
					for (int j = 0; j < attributes.length; j ++) {
						if (Modifier.isPrivate(attributes[j].getModifiers()) && !attributes[j].isSynthetic()) {
							this.getGetterOrSetter("set", attributes[j].getName(), obj).invoke(obj, currentNode.get(attributes[j].getName()));
						}
					}
				}
			}	
		}
		catch (FileNotFoundException e) {
			throw new ElementInDBNotFoundException(this.getPathToLoadFrom(obj, primaryKeyValues));
		}
	}
}
