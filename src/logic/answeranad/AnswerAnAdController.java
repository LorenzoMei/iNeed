package logic.answeranad;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.EnumSet;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.AnswerAnAdBean;
import logic.beans.KeyAnswersAnAdBean;
import logic.dao.AnswerNotFoundException;
import logic.dao.DAOAnswer;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Answer;
import logic.misc.NoSuchSetterException;
import logic.misc.ReflectionMiscellaneous;

public class AnswerAnAdController {

	Logger logger = Logger.getLogger(this.getClass().getName());
	private static AnswerAnAdController reference = null;
	
	public static AnswerAnAdController getInstance() {
		if(reference == null)
			reference = new AnswerAnAdController();
		return reference;
	}
	
	private AnswerAnAdController() {}
	
	public void answer(AnswerAnAdBean answerAnAdBean) throws UserAlreadyAnsweredException{
		
		Answer answer = new Answer();
		DAOAnswer dao = (DAOAnswer) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.ANSWER);
		try {
			dao.loadAnswer((int) answerAnAdBean.getAttributes().get(KeyAnswersAnAdBean.ANSWERANADBEAN_ID),
					(String) answerAnAdBean.getAttributes().get(KeyAnswersAnAdBean.ANSWERANADBEAN_TYPE),
					(String) answerAnAdBean.getAttributes().get(KeyAnswersAnAdBean.ANSWERANADBEAN_USERNAME),
					answer);
			throw new UserAlreadyAnsweredException(answer.getDate());
		} catch (AnswerNotFoundException e) {
			EnumSet.allOf(KeyAnswersAnAdBean.class).forEach(key ->{
				try {
					ReflectionMiscellaneous.getSetter(key.getKeyName(), answer).invoke(answer, answerAnAdBean.getAttributes().get((Object) key));
				} catch (NoSuchSetterException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					logger.log(Level.SEVERE, e1.toString());
				}
			});
			answer.setDate(Calendar.getInstance());
			dao.storeAnswer(answer);
		}
	}
		
}
