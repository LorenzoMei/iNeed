package logic.answeranad;

import java.lang.reflect.InvocationTargetException;

import logic.beans.AnswerAnAdBean;

public interface AnswerAnAdControllerInterface {

	public void candidate(AnswerAnAdBean answerAnAdBean) throws IllegalAccessException, InvocationTargetException;
}
