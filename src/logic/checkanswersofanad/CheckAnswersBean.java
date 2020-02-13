package logic.checkanswersofanad;

import logic.entity.Ad;

public class CheckAnswersBean {
	
	private Ad ad;
	
	private CheckAnswersBean() {	
		this.ad = null;
	}
	
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	public Ad getAd() {
		return this.ad;
	}	
}
