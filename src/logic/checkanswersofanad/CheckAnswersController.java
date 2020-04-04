package logic.checkanswersofanad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.ActionOnAnswerBean;
import logic.beans.CheckAnswersBean;
import logic.dao.AdNotFoundException;
import logic.dao.AnswerNotFoundException;
import logic.dao.DAOAd;
import logic.dao.DAOAnswer;
import logic.dao.DAOFactory;
import logic.dao.DAOFavor;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.Ad;
import logic.entity.AdFactory;
import logic.entity.Ads;
import logic.entity.Answer;
import logic.entity.Favor;
import logic.entity.OfferAd;
import logic.entity.RequestAd;
import logic.entity.User;

public class CheckAnswersController {
	
	private static CheckAnswersController instance;
	
	public static CheckAnswersController getInstance() {
		if(instance == null) {
			instance = new CheckAnswersController();
		}
		return instance;
	}
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private DAOFavor daoFavor = (DAOFavor) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.FAVOR);
	private DAOUser daoUser = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
	private DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
	private DAOAnswer daoAnswers = (DAOAnswer) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.ANSWER);
	
	private CheckAnswersController() {
	}
	
	public void answersList(CheckAnswersBean answersBean){
		String type = RequestAd.class.getSimpleName();
		
		List <Answer> answersList = new ArrayList<>();
		
		if(answersBean.getAdType() == OfferAd.class.getSimpleName()) 
			type = OfferAd.class.getSimpleName();
		
		daoAnswers.loadAnswers(answersBean.getAdId(), type, answersList);
		
		for(int i = 0; i < answersList.size(); i++){
			if(answersList.get(i).isDenied()) {
				System.out.println("Nome: " + answersList.get(i).getUsername());
				answersList.remove(i);
				i--;
			}
		}
		answersBean.setAnswersList(answersList);
	}
	
	public void acceptAnswer(ActionOnAnswerBean bean) throws RequestAdHasAlreadyAnAnswerAcceptedException, AnswerAlreadyAcceptedException {
		
		
		Ad ad = null;
		for (Ads t : Ads.values()) {
			if (t.getName().compareTo(bean.getAdType()) == 0) {
				ad = AdFactory.getReference().typePost(t);
				break;
			}
		}
		User answerer = new User();
		User answered = new User();
		try {
			this.daoUser.loadUser(answerer, bean.getAnswererUsername());
			this.daoUser.loadUser(answered, bean.getAnsweredUsername());
			logger.log(Level.INFO, String.format("bean.adType is: %s", bean.getAdType()));
			logger.log(Level.INFO, String.format("bean.adId is: %d", bean.getAdId()));
			logger.log(Level.INFO, String.format("ad reference is: %s", ad));
			this.daoAd.loadAd(ad, bean.getAdId());
			if (bean.getAdType().compareTo(Ads.REQUEST.getName()) == 0) {
				this.acceptRequestAnswer(bean, answerer, answered, ad);
			}
			else {
				this.acceptOfferAnswer(bean, answered, answerer, ad);
			}	
		} catch (UserNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
		} catch (AdNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	private void changeAcceptedAnswer(Favor favor, User newOfferer) {
//		replace current favor with a new one
		logger.log(Level.INFO, String.format("changeAcceptedAnswer invoked"));
		this.daoFavor.deleteFavor(favor.getOfferer(), favor.getRequester(), favor.getDateOfRequest());
		Favor newFavor = new Favor();
		newFavor.setAd(favor.getAd());
		newFavor.setOfferer(newOfferer);
		newFavor.setRequester(favor.getRequester());
		newFavor.setDateOfRequest(Calendar.getInstance());
		this.daoFavor.storeFavor(newFavor);
	}
	
	private void acceptRequestAnswer(ActionOnAnswerBean bean, User offerer, User requester, Ad ad) throws AnswerAlreadyAcceptedException, RequestAdHasAlreadyAnAnswerAcceptedException {
		List<Favor> favors = this.daoFavor.loadFavorOfRequestAd(requester, ad);
		if (favors.size() == 0) { 
			logger.log(Level.INFO, "storing favor");
			Favor favor = new Favor();
			favor.setAd(ad);
			favor.setOfferer(offerer);
			favor.setRequester(requester);
			favor.setDateOfRequest(Calendar.getInstance());
			this.daoFavor.storeFavor(favor);
		}
		else {
			if (favors.get(0).getOfferer().getUsername().compareTo(offerer.getUsername()) == 0) {
				throw new AnswerAlreadyAcceptedException(favors.get(0).getDateOfRequest());
			}
			else {
				if (!bean.isConfirmed()) {
					logger.log(Level.INFO, "bean is not confirmed");
					throw new RequestAdHasAlreadyAnAnswerAcceptedException(favors.get(0));
				}
				else {
					this.changeAcceptedAnswer(favors.get(0), offerer);
				}
			}
		}
	}
	
	private void acceptOfferAnswer(ActionOnAnswerBean bean, User offerer, User requester, Ad ad) { 
		Favor favor = new Favor();
		favor.setAd(ad);
		favor.setOfferer(offerer);
		favor.setRequester(requester);
		favor.setDateOfRequest(Calendar.getInstance());
		this.daoFavor.storeFavor(favor);
	}
	
	public void denyAnswer(ActionOnAnswerBean bean) throws AnswerNotFoundException {
		Answer answer = new Answer();
		this.daoAnswers.loadAnswer(bean.getAdId(), bean.getAdType(), bean.getAnswererUsername(), answer);
		logger.log(Level.INFO, answer.isDenied().toString());
		answer.setDenied(true);
		this.daoAnswers.storeAnswer(answer);
	}
}
