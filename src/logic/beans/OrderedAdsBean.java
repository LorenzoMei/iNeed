package logic.beans;

import java.util.List;

import logic.entity.Ad;
import logic.viewanad.ViewAnAdControllerInterface;

public class OrderedAdsBean {
	
	private List<Ad> ads;
	private String order;
	
	public OrderedAdsBean() {
		this.setOrderUnsorted();
	}
	
	public List<Ad> getAds(){
		return this.ads;
	}
	
	public String getOrder() {
		return this.order;
	}
	
	public void setAds(List<Ad> val) {
		this.ads = val;
	}
	
	
	private void setOrder(String order) {
		this.order = order;
	}
	
	public void setOrderByTime() {
		this.setOrder(ViewAnAdControllerInterface.BY_TIME);
	}
	
	public void setOrderUnsorted() {
		this.setOrder(ViewAnAdControllerInterface.UNSORTED);
	}
}
