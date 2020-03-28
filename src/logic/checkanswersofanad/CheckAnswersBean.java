package logic.checkanswersofanad;

import java.util.List;

import logic.entity.Ad;
import logic.entity.Answer;

public class CheckAnswersBean {
	
	private Ad ad;
	private List<Answer> answersList;
	
	public CheckAnswersBean() {	
		this.ad = null;
	}
	
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	public void setAnswersList(List<Answer> answersList) {
		this.answersList = answersList;
	}
	
	public Ad getAd() {
		return this.ad;
	}	

	public List<Answer> getAnswersList(){
		return this.answersList;
	}
}
