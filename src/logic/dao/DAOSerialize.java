package logic.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAOSerialize {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected static final String PRIMARY_KEY_VALUES_SEPARATOR = "#";
	protected static final String SERIALIZED_EXTENSION = ".ser";
	protected static final String DATE_TIME_FORMAT_STANDARD = "YYYY-MM-dd-HH-mm-ss";
	protected static final String DATE_TIME_SEPARATOR = "-";
	protected static final String DBPATH = "db" + File.separator + "serialized" + File.separator;
	
	protected String[] tokenize(File stored){
		return this.tokenize(stored.getName());
	}
	
	protected String[] tokenize(String storedName){
		return storedName.split(DAOSerialize.PRIMARY_KEY_VALUES_SEPARATOR);
	}
	
	protected String readDBPath() {
		return DBPATH;
	}

	private String getPath(Object obj, List <String> primaryKeyValues){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.readDBPath() + obj.getClass().getSimpleName() + File.separator);
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
	
	protected void store(Object obj, List <String> primaryKeyValues) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		logger.log(Level.INFO, this.getPath(obj, primaryKeyValues));
		try (
				FileOutputStream dest = new FileOutputStream(this.getPath(obj, primaryKeyValues));
				DAOSerializeOOS writer = new DAOSerializeOOS(dest);){
			logger.log(Level.INFO, "Destination: {0}", dest);
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
	protected void delete(Object obj, List <String> primaryKeyValues) throws IOException {

		Path path = Paths.get(this.getPath(obj, primaryKeyValues));
		Files.delete(path);
	}
}