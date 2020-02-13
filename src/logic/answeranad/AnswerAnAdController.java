package logic.answeranad;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import logic.dao.DAOAnswers;
import logic.dao.DAOFactory;
import logic.entity.Answer;

public class AnswerAnAdController implements AnswerAnAdControllerInterface{

	private static AnswerAnAdController reference = null;
	
	public static AnswerAnAdController getInstance() {
		if(reference == null)
			reference = new AnswerAnAdController();
		return reference;
	}
	
	private AnswerAnAdController() {};
	
	public void candidate(AnswerAnAdBean answerAnAdBean) throws IllegalAccessException, InvocationTargetException {
		Answer answers = new Answer();
		
		Method[] methodsBean = answerAnAdBean.getClass().getMethods();
		Method[] methodsEntity = answers.getClass().getMethods();
		
		for(int i = 0; i < methodsBean.length; i++) {
			if(methodsBean[i].getName().contains("get")) {
				for(int j = 0; j < methodsEntity.length; j++) {
					if(methodsEntity[j].getName().contains("set" + methodsBean[i].getName().substring(3, 4).toUpperCase() + methodsBean[i].getName().substring(4))) {
						Object value = methodsBean[i].invoke(answerAnAdBean, (Object[]) null);
						methodsEntity[j].invoke(answers, value);
					}
				}
			}
		}
		
		DAOAnswers dao = (DAOAnswers) DAOFactory.getReference().getDAOReference("Answers");
		dao.storeAnswers(answers);
	}
}
