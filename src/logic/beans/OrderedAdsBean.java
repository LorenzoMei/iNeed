package logic.beans;

import java.util.List;

import logic.entity.Ad;
import logic.viewanad.Order;

public class OrderedAdsBean {
	
	private List<Ad> ads;
	private Order order;
	
	public OrderedAdsBean() {
		this.setOrderUnsorted();
	}
	
	public List<Ad> getAds(){
		return this.ads;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public void setAds(List<Ad> val) {
		this.ads = val;
	}
	
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setOrderByTime() {
		this.setOrder(Order.TIME);
	}
	
	public void setOrderUnsorted() {
		this.setOrder(Order.UNSORTED);
	}
}
