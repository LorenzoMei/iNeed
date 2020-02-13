package logic.dao;

import java.util.List;

public interface DAOAnswers {
	
	public void loadAnswers(int id, String type, List<String> answersList);
}
