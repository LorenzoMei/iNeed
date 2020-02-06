package logic.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.json.simple.parser.ParseException;

public interface DAOJson {

	public void store(Object obj, List<String> primaryKeyNames) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	void load(Object obj, List <String> primaryKeyNames, List <String> primaryKeyValues) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, IOException, ParseException, ElementInDBNotFoundException;
	
	
}
