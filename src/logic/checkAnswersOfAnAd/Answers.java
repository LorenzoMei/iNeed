package logic.CheckAnswersOfAnAd;

import java.util.ArrayList;

public class Answers {
	
	private ArrayList <String> answersList = new ArrayList <String>();
	
	public ArrayList <String> getAnswersList() {
		return this.answersList;
	}
	
	public void setAnswersList(String user) {
		this.answersList.add(user);
	}
}
