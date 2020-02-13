package test.answers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import logic.answeranad.AnswerAnAdBean;
import logic.answeranad.AnswerAnAdController;
import logic.answeranad.AnswerAnAdControllerInterface;
import logic.checkanswersofanad.CheckAnswersBean;
import logic.checkanswersofanad.CheckAnswersController;
import logic.checkanswersofanad.CheckAnswersControllerInterface;
import logic.entity.Ad;
import logic.entity.RequestAd;

class TestAnswers {

	@Test
	void testAnswers() throws IllegalAccessException, InvocationTargetException {
		String[] candidati = new String[3];
		candidati[0] = "Pippo";
		candidati[1] = "Pluto";
		candidati[2] = "Paperino";
		
		AnswerAnAdBean answerAnAdBean = new AnswerAnAdBean();
		answerAnAdBean.setId(1);
		answerAnAdBean.setType("RequestAd");
		
		AnswerAnAdControllerInterface controller = AnswerAnAdController.getInstance();	
		
		for(int i = 0; i < 3; i++) {
			answerAnAdBean.setUsername(candidati[i]);
			controller.candidate(answerAnAdBean);
		}
		
		Ad ad = new RequestAd();
		ad.setId(1);
		ad.setTitle("AIUTO! Ho bisogno di un passaggio");
		ad.setBody("Devo andare in aeroporto domani mattina");
		
		CheckAnswersBean checkAnswersBean = new CheckAnswersBean();
		checkAnswersBean.setAd(ad);
		
		CheckAnswersControllerInterface controllerAnswers = CheckAnswersController.getInstance();
		controllerAnswers.answersList(checkAnswersBean);
		
		List<String> answers = checkAnswersBean.getAnswersList();
		
		for(int i = 0; i < answers.size(); i++) {
			System.out.println("CANDIDATO " + i + " : " + answers.get(i));
			Assert.assertTrue(answers.contains(candidati[i]));	
		}
 	}

}
