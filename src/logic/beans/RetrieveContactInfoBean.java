package logic.beans;

import java.util.HashMap;
import java.util.Map;

public class RetrieveContactInfoBean {
	private String username;
	private Map<ContactInfo, String> infos = new HashMap<>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void putInfo(ContactInfo key, String value) {
		this.infos.put(key, value);
	}
	public String getInfo(ContactInfo key) {
		return this.infos.get(key);
	}
}
