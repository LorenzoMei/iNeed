package logic.checkanswersofanad;

import java.util.ArrayList;
import java.util.List;

import logic.dao.DAOAnswers;
import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.entity.OfferAd;

public class CheckAnswersController implements CheckAnswersControllerInterface{
	
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
		Ad ad = answersBean.getAd();
		String type = "RequestAd";
		
		List <String> answersList = new ArrayList<>();
		
		DAOAnswers daoAnswers= (DAOAnswers) DAOFactory.getReference().getDAOReference("Answers");
		
		if(ad instanceof OfferAd) 
			type = "OfferAd";
		
		daoAnswers.loadAnswers(ad.getId(), type, answersList);
		answersBean.setAnswersList(answersList);
	}
}
