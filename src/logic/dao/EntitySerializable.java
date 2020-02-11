package logic.dao;

import java.io.Serializable;
import java.util.HashMap;

public class EntitySerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String entityClassName;
	private HashMap<String, Object> attributes;
	
	public String getEntityClassName() {
		return this.entityClassName;
	}
	
	public HashMap<String, Object> getAttributes(){
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
		}
		return this.attributes;
	}
	
	public void setEntityClassName(String val) {
		this.entityClassName = val;
	}
	
	public void setAttributes(HashMap <String, Object> val) {
		
		this.attributes = val;
	}
}
