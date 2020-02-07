package logic.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EntitySerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> attributes;
	
	public Map<String, Object> getAttributes(){
		if (this.attributes == null) {
			this.attributes = new HashMap<String, Object>();
		}
		return this.attributes;
	}
	
	public void setAttributes(Map <String, Object> val) {
		
		this.attributes = val;
	}
}
