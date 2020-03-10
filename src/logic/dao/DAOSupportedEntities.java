package logic.dao;

import logic.entity.Ad;
import logic.entity.User;
import logic.entity.Message;
import logic.entity.Answer;
import logic.entity.Review;
import logic.entity.Favor;


public enum DAOSupportedEntities {
	AD (Ad.class.getSimpleName()),
	USER (User.class.getSimpleName()),
	MESSAGE (Message.class.getSimpleName()),
	ANSWER (Answer.class.getSimpleName()),
	REVIEW (Review.class.getSimpleName()),
	FAVOR (Favor.class.getSimpleName()),
	;
	
	private String entityName;
	
	private DAOSupportedEntities(String entityName) {
		this.entityName = entityName;
	}
	
	public String getEntityName() {
		return this.entityName;
	}
}
