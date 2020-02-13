package logic.checkanswersofanad;

import java.util.List;

import logic.entity.Ad;

public class CheckAnswersBean {
	
	private Ad ad;
	private List<String> answersList;
	
	public CheckAnswersBean() {	
		this.ad = null;
	}
	
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	public void setAnswersList(List<String> answersList) {
		this.answersList = answersList;
	}
	
	public Ad getAd() {
		return this.ad;
	}	

	public List<String> getAnswersList(){
		return this.answersList;
	}
}
