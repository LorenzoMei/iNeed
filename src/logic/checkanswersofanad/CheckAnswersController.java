package logic.checkanswersofanad;

import java.util.ArrayList;
import java.util.List;

import logic.beans.CheckAnswersBean;
import logic.dao.DAOAnswer;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Answer;
import logic.entity.OfferAd;

public class CheckAnswersController {
	
	private static CheckAnswersController instance;
	
	public static CheckAnswersController getInstance() {
		if(instance == null) {
			instance = new CheckAnswersController();
		}
		return instance;
	}
	
	private CheckAnswersController() {
	}
	
	public void answersList(CheckAnswersBean answersBean){
		String type = "RequestAd";
		
		List <Answer> answersList = new ArrayList<>();
		
		DAOAnswer daoAnswers= (DAOAnswer) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.ANSWER);
		
		if(answersBean.getAdType() == OfferAd.class.getSimpleName()) 
			type = OfferAd.class.getSimpleName();
		
		daoAnswers.loadAnswers(answersBean.getAdId(), type, answersList);
		answersBean.setAnswersList(answersList);
	}
}
