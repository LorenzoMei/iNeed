package logic.dao;

import java.util.List;

import logic.entity.Answer;

public interface DAOAnswer {
	
	public void loadAnswers(int id, String type, List<Answer> answersList);
	public void loadAnswer(int id, String type, String Username, Answer answer) throws AnswerNotFoundException;
	public void storeAnswer(Answer answers);
}
