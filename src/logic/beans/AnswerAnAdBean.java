package logic.beans;

import java.util.HashMap;
import java.util.Map;

public class AnswerAnAdBean {
	
	private Map<KeyAnswersAnAdBean, Object> attributes = new HashMap<>();
	
	public void setId(int id) {
		attributes.put(KeyAnswersAnAdBean.ANSWERANADBEAN_ID, id);
	}
	
	public void setUsername(String username) {
		attributes.put(KeyAnswersAnAdBean.ANSWERANADBEAN_USERNAME, username);
	}
	
	public void setType(String type) {
		attributes.put(KeyAnswersAnAdBean.ANSWERANADBEAN_TYPE, type);
	}
	
	public Map<KeyAnswersAnAdBean, Object> getAttributes(){
		return this.attributes;
	}
}
