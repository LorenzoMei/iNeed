package logic.beans;

import java.util.Calendar;
import java.util.List;

import logic.entity.Answer;

public class CheckAnswersBean {
	
	private int adId;
	private String adType;
	private List<Answer> answersList;
	
	public int getNumOfAnswers() {
		return this.answersList.size();
	}
	
	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}
	
	public void setAnswersList(List<Answer> answersList) {
		this.answersList = answersList;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getAnswerer(int i) {
		return this.answersList.get(i).getUsername();
	}
	public Calendar getDate(int i) {
		return this.answersList.get(i).getDate();
	}
}
