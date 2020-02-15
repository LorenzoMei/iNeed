package logic.answeranad;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.AnswerAnAdBean;
import logic.beans.KeyAnswersAnAdBean;
import logic.dao.DAOAnswer;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Answer;
import logic.misc.NoSuchSetterException;
import logic.misc.ReflectionMiscellaneous;

public class AnswerAnAdController implements AnswerAnAdControllerInterface {

	Logger logger = Logger.getLogger(this.getClass().getName());
	private static AnswerAnAdController reference = null;
	
	public static AnswerAnAdController getInstance() {
		if(reference == null)
			reference = new AnswerAnAdController();
		return reference;
	}
	
	private AnswerAnAdController() {};
	
	public void candidate(AnswerAnAdBean answerAnAdBean) throws IllegalAccessException, InvocationTargetException {
		
		Answer answers = new Answer();
		
		EnumSet.allOf(KeyAnswersAnAdBean.class).forEach(key ->{
			try {
				ReflectionMiscellaneous.getSetter(key.getKeyName(), answers).invoke(answers, answerAnAdBean.getAttributes().get((Object) key));
			} catch (NoSuchSetterException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.log(Level.SEVERE, e.toString());
			}
		});
		
		DAOAnswer dao = (DAOAnswer) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.ANSWER);
		dao.storeAnswers(answers);
	}
}
