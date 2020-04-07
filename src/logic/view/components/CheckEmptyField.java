package logic.view.components;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextInputControl;
import logic.misc.NoSuchGetterException;
import logic.misc.ReflectionMiscellaneous;

public class CheckEmptyField {
	Logger logger = Logger.getLogger(this.getClass().getName());
	private List<TextInputControl> textInputFields;
	
	
	protected CheckEmptyField(){
		this.textInputFields = new ArrayList<>();
	}
	
	public List<TextInputControl> getTextInputFields(){
		return this.textInputFields;
	}
	


	public void populateTextInputFields(Object obj){
		logger.log(Level.INFO, "Starting population: ");
		Field[] fieldArrayAttributes = obj.getClass().getDeclaredFields();
		Class<FXML> classAnnotation = javafx.fxml.FXML.class; 
		logger.log(Level.INFO, "Annotazione: " + classAnnotation.getName());
		for(int i = 0; i < fieldArrayAttributes.length; i++ ) {
			
				String printThis = "file attributes:  " + fieldArrayAttributes[i].getName();
				logger.log(Level.INFO, printThis);
				if(Modifier.isPrivate(fieldArrayAttributes[i].getModifiers()) 
						&& !fieldArrayAttributes[i].isSynthetic() 
						&& fieldArrayAttributes[i].getAnnotation(classAnnotation) != null 
						&& fieldArrayAttributes[i].getType().getSimpleName().contains("Field") ) {
					
						try {
							textInputFields.add((TextInputControl)ReflectionMiscellaneous.getGetter( fieldArrayAttributes[i].getName(), obj).invoke(obj, (Object[]) null));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchGetterException e) {
							logger.log(Level.SEVERE, String.format(" %s  Fail in CheckEMptyFields, Set/get not found",  e.toString()) );

						}

				}
		}
	}
}
