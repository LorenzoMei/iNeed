package logic.checkanswersofanad;

import java.util.ArrayList;
import java.util.List;

public class Answers {
	
	private List <String> answersList = new ArrayList <>();
	
	public List <String> getAnswersList() {
		return this.answersList;
	}
	
	public void setAnswersList(String user) {
		this.answersList.add(user);
	}
}
