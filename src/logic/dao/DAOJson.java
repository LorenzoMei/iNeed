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

public abstract class DAOJson {
	
	private String DBPath = readDBPath();
	
	private String readDBPath() {
//		TODO stub
		
		return "db/json/";
	}
	
	private Boolean checkPrimaryKey(JSONObject node, List<String> primaryKeyNames, List<String> primaryKeyValues) {
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			if (!node.get(primaryKeyNames.get(k)).equals(primaryKeyValues.get(k))){
				return false;
			}
		}
		return true;
	}
	
	private String getPathToStoreIn(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		String path = DBPath + obj.getClass().getSimpleName() + "/";
		for (int k = 0; k < primaryKeyNames.size(); k ++) {
			path += this.getGetterOrSetter("get", primaryKeyNames.get(k), obj).invoke(obj, (Object[])null);
		}
		path += ".json";
		
		return path;
	}
	
	private String getPathToLoadFrom(Object obj, List <String> primaryKeyValues) {
		String path = DBPath + obj.getClass().getSimpleName() + "/";
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			path += primaryKeyValues.get(k);
		}
		path += ".json";
		
		return path;
	}
	
	private Method getGetterOrSetter(String getOrSet, String attrName, Object obj) throws NoSuchMethodException {
		
		Method[] methods = obj.getClass().getMethods();
		for (int j = 0; j < methods.length; j ++) {
			if (methods[j].getName().contains(getOrSet) && methods[j].getName().contains(attrName.substring(0, 1).toUpperCase() + attrName.substring(1))) {
				return methods[j];
			}
		}
		throw new NoSuchMethodException();
	}
	
	protected void store(Object obj, List<String> primaryKeyNames) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		
//		It stores object data in a JSON array, coping values of all Object attributes using reflection
		
		File destDB = new File(this.getPathToStoreIn(obj, primaryKeyNames));
		try (FileWriter writer = new FileWriter(destDB)) {
			JSONObject values = new JSONObject();
			JSONArray root = new JSONArray();
			Field[] attributes = obj.getClass().getDeclaredFields();
						
			for (int i = 0; i < attributes.length; i ++) {
				if (Modifier.isPrivate(attributes[i].getModifiers())) {
					values.put(attributes[i].getName(), this.getGetterOrSetter("get", attributes[i].getName(), obj).invoke(obj, (Object[]) null));
				}
			}
			root.add(values);
			root.writeJSONString(writer); 
		}

	}
	
	protected void load(Object obj, List <String> primaryKeyNames, List <String> primaryKeyValues) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, IOException, ParseException, ElementInDBNotFoundException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		File srcDB = new File(this.getPathToLoadFrom(obj, primaryKeyValues));
		try (FileReader reader = new FileReader(srcDB)){
			JSONArray root = (JSONArray) JSONValue.parseWithException(reader);
			for (int i = 0; i < root.size(); i ++) {
				JSONObject currentNode = (JSONObject) root.get(i);
				if (this.checkPrimaryKey(currentNode, primaryKeyNames, primaryKeyValues)) {
					Field[] attributes = obj.getClass().getDeclaredFields();
					for (int j = 0; j < attributes.length; j ++) {
						if (Modifier.isPrivate(attributes[j].getModifiers())) {
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
