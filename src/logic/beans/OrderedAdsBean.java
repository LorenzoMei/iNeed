package logic.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import logic.entity.Ad;
import logic.misc.Order;
public class OrderedAdsBean {
	
	private List<Ad> ads = new ArrayList<>();
	private Order order;
	
	public OrderedAdsBean() {
		this.setOrderUnsorted();
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	private void setOrder(Order order) {
		this.order = order;
	}
	
	public void setOrderByTime() {
		this.setOrder(Order.TIME);
	}
	
	public void setOrderUnsorted() {
		this.setOrder(Order.UNSORTED);
	}
	
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}
	
	public List<String> getAllTitles(){
		List<String> result = new ArrayList<>();
		for (int i = 0; i < ads.size(); i ++) {
			result.add(ads.get(i).getTitle());
		}
		return result;
	}
	
	public List<String> getAllBodies(){
		List<String> result = new ArrayList<>();
		for (int i = 0; i < ads.size(); i ++) {
			result.add(ads.get(i).getBody());
		}
		return result;
	}
	
	public List<Calendar> getAllDatesOfPublication(){
		List<Calendar> result = new ArrayList<>();
		for (int i = 0; i < ads.size(); i ++) {
			result.add(ads.get(i).getData());
		}
		return result;
	}
	
	public List<String> getAllOwners(){
		List<String> result = new ArrayList<>();
		for (int i = 0; i < ads.size(); i ++) {
			result.add(ads.get(i).getOwnerUsername());
		}
		return result;
	}
}
