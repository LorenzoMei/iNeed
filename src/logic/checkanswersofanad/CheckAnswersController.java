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
		logger.log(Level.INFO, "CheckAnswersController initiated");
	}
	
	public void answersList(CheckAnswersBean answersBean){
		
		logger.log(Level.INFO, String.format("answersBean values of ad id and type are: %d, %s)", answersBean.getAdId(), answersBean.getAdType()));
		int i = 0;
		String type = RequestAd.class.getSimpleName();
		
		List <Answer> answersList = new ArrayList<>();
		
		if(answersBean.getAdType().compareTo(OfferAd.class.getSimpleName()) == 0) {
			type = OfferAd.class.getSimpleName();
		}
		
		daoAnswers.loadAnswers(answersBean.getAdId(), type, answersList);
				
		while(i < answersList.size()) {
			if(answersList.get(i).isDenied()) {
				answersList.remove(i);
			}
			else {
				i++;
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
				this.acceptOfferAnswer(answered, answerer, ad);
			}	
		} catch (UserNotFoundException | AdNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	private void changeAcceptedAnswer(Favor oldFavor, User newOfferer) {
//		replace current favor with a new one
		logger.log(Level.INFO, "changeAcceptedAnswer invoked");
		User oldOfferer = new User();
		User requester = new User();
		try {
			daoUser.loadUser(oldOfferer, oldFavor.getOffererUsername());
			daoUser.loadUser(requester, oldFavor.getRequesterUsername());
		} catch (UserNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		this.daoFavor.deleteFavor(oldOfferer, requester, oldFavor.getDateOfRequest());
		Favor newFavor = new Favor();
		newFavor.setAdId(oldFavor.getAdId());
		newFavor.setAdType(oldFavor.getAdType());
		newFavor.setOffererUsername(newOfferer.getUsername());
		newFavor.setRequesterUsername(oldFavor.getRequesterUsername());
		newFavor.setDateOfRequest(Calendar.getInstance());
		this.daoFavor.storeFavor(newFavor);
	}
	
	private void acceptRequestAnswer(ActionOnAnswerBean bean, User offerer, User requester, Ad ad) throws AnswerAlreadyAcceptedException, RequestAdHasAlreadyAnAnswerAcceptedException {
		List<Favor> favors = this.daoFavor.loadFavorsByAd(requester, ad);
		if (favors.size() == 0) { 
			logger.log(Level.INFO, "storing favor");
			Favor favor = new Favor();
			favor.setAdId(ad.getId());
			favor.setAdType(ad.getType());
			favor.setOffererUsername(offerer.getUsername());
			favor.setRequesterUsername(requester.getUsername());
			favor.setDateOfRequest(Calendar.getInstance());
			this.daoFavor.storeFavor(favor);
		}
		else {
			if (favors.get(0).getOffererUsername().compareTo(offerer.getUsername()) == 0) {
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
	
	private void acceptOfferAnswer(User offerer, User requester, Ad ad) { 
		Favor favor = new Favor();
		favor.setAdId(ad.getId());
		favor.setAdType(ad.getType());
		favor.setOffererUsername(offerer.getUsername());
		favor.setRequesterUsername(requester.getUsername());
		favor.setDateOfRequest(Calendar.getInstance());
		this.daoFavor.storeFavor(favor);
	}
	
	public void denyAnswer(ActionOnAnswerBean bean) throws AnswerNotFoundException {
		
		// PLEASE NOTE: this method assumes that currently there can't be more than one answer with same answerer and answered. This is not ideal, so it may change in the future.
						
		try {
			Answer answer = new Answer();
			User answerer = new User();
			User answered = new User();
			Ad ad = null;
			List<Favor> favors = null;
			User offererFavorToDelete = new User();
			User requesterFavorToDelete = new User();
			for (Ads t : Ads.values()) {
				if (t.getName().compareTo(bean.getAdType()) == 0) {
					ad = AdFactory.getReference().typePost(t);
					break;
				}
			}
			this.daoUser.loadUser(answerer, bean.getAnswererUsername());
			this.daoUser.loadUser(answered, bean.getAnsweredUsername());
			this.daoAd.loadAd(ad, bean.getAdId());
			if (bean.getAdType().compareTo(Ads.REQUEST.getName()) == 0) {
				favors = this.daoFavor.loadFavorsByAd(answered, answerer, ad);
			}
			else {
				favors = this.daoFavor.loadFavorsByAd(answerer, answered, ad);
			}
			deleteFavor(requesterFavorToDelete, offererFavorToDelete, favors, answer, bean);
		} catch (UserNotFoundException | AdNotFoundException e) {
			logger.log(Level.SEVERE, e.toString());
		}		
	}
	
	private void deleteFavor(User requesterFavorToDelete, User offererFavorToDelete,
			List<Favor> favors, Answer answer, ActionOnAnswerBean bean) throws UserNotFoundException, AnswerNotFoundException{
		try {
			this.daoUser.loadUser(requesterFavorToDelete, favors.get(0).getRequesterUsername());
			this.daoUser.loadUser(offererFavorToDelete, favors.get(0).getOffererUsername());
			this.daoFavor.deleteFavor(offererFavorToDelete, requesterFavorToDelete, favors.get(0).getDateOfRequest());
		} catch (IndexOutOfBoundsException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		finally {
			this.daoAnswers.loadAnswer(bean.getAdId(), bean.getAdType(), bean.getAnswererUsername(), answer);
			logger.log(Level.INFO, answer.isDenied().toString());
			answer.setDenied(true);
			this.daoAnswers.storeAnswer(answer);
		}	
		
	}
	
}
