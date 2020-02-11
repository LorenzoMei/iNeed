package logic.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EntitySerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String entityClassName;
	private Map<String, Serializable> attributes;
	
	public String getEntityClassName() {
		return this.entityClassName;
	}
	
	public Map<String, Serializable> getAttributes(){
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
		}
		return this.attributes;
	}
	
	public void setEntityClassName(String val) {
		this.entityClassName = val;
	}
	
	
}
