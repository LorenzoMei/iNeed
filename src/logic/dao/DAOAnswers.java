package logic.dao;

import java.util.List;

import logic.entity.Answer;

public interface DAOAnswers {
	
	public void loadAnswers(int id, String type, List<String> answersList);
	public void storeAnswers(Answer answers);
}
