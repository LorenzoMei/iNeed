package logic.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

public abstract class DAOSerialize {

	Logger logger = Logger.getLogger(this.getClass().getName());
	private String dBPath = readDBPath();
	
	protected static final String PRIMARY_KEY_VALUES_SEPARATOR = "#";
	protected static final String SERIALIZED_EXTENSION = ".ser";
	
	protected String readDBPath() {
//		TODO: stub
		return "db" + File.separator + "serialized" + File.separator;
	}
	
//	private String getPathToLoadFrom(Object obj, List <String> primaryKeyValues) {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
//		for (int k = 0; k < primaryKeyValues.size(); k ++) {
//			stringBuilder.append(primaryKeyValues.get(k));
//			stringBuilder.append(DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR);
//		}
//		stringBuilder.append(DAOSerialize.SERIALIZED_EXTENSION);
//		return stringBuilder.toString();
//	}

	private String getPath(Object obj, List <String> primaryKeyValues) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dBPath + obj.getClass().getSimpleName() + File.separator);
		File test = new File(stringBuilder.toString());
		if (!test.exists()) {
			test.mkdirs();
		}
		for (int k = 0; k < primaryKeyValues.size(); k ++) {
			stringBuilder.append(primaryKeyValues.get(k));
			stringBuilder.append(DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR);
		}
		stringBuilder.append(DAOSerialize.SERIALIZED_EXTENSION);
		return stringBuilder.toString();
	}
	
	protected void store(Object obj, List <String> primaryKeyNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		try (
				FileOutputStream dest = new FileOutputStream(this.getPath(obj, primaryKeyNames));
				DAOSerializeOOS writer = new DAOSerializeOOS(dest);){
			writer.writeObject(obj);
		}
	}
	protected void load(Object obj, List <String> primaryKeyValues) throws IOException, ClassNotFoundException, ElementInDBNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		
//		Searches in every node of the root in DB for a User with the same username and passw as the ones provided.
//		@ return User if retrieved, null otherwise
		
		try (
				FileInputStream src = new FileInputStream(this.getPath(obj, primaryKeyValues));
				DAOSerializeOIS reader = new DAOSerializeOIS(src, obj);){
			reader.readObject();
		}
		catch (FileNotFoundException e) {
			throw new ElementInDBNotFoundException(this.getPath(obj, primaryKeyValues), e);
		}
	}
}