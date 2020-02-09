package logic.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EntitySerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String entityClassName;
	private Map<String, Object> attributes;
	
	public String getEntityClassName() {
		return this.entityClassName;
	}
	
	public Map<String, Object> getAttributes(){
		if (this.attributes == null) {
			this.attributes = new HashMap<String, Object>();
		}
		return this.attributes;
	}
	
	public void setEntityClassName(String val) {
		this.entityClassName = val;
	}
	
	public void setAttributes(Map <String, Object> val) {
		
		this.attributes = val;
	}
}
