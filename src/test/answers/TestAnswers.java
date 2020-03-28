package test.answers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import logic.answeranad.AnswerAnAdController;
import logic.answeranad.UserAlreadyAnsweredException;
import logic.beans.AnswerAnAdBean;
import logic.checkanswersofanad.CheckAnswersBean;
import logic.checkanswersofanad.CheckAnswersController;
import logic.entity.Ad;
import logic.entity.Answer;
import logic.entity.RequestAd;


public class TestAnswers {

	@Test
	public void testAnswers() throws IllegalAccessException, UserAlreadyAnsweredException {
		String[] candidati = new String[3];
		candidati[0] = "Pippo";
		candidati[1] = "Pluto";
		candidati[2] = "Paperino";
		
		AnswerAnAdBean answerAnAdBean = new AnswerAnAdBean();
		answerAnAdBean.setId(1);
		answerAnAdBean.setType("RequestAd");
		
		AnswerAnAdController controller = AnswerAnAdController.getInstance();	
		
		for(int i = 0; i < 3; i++) {
			answerAnAdBean.setUsername(candidati[i]);
			controller.answer(answerAnAdBean);
		}
		
		Ad ad = new RequestAd();
		ad.setId(1);
		ad.setTitle("AIUTO! Ho bisogno di un passaggio");
		ad.setBody("Devo andare in aeroporto domani mattina");
		
		CheckAnswersBean checkAnswersBean = new CheckAnswersBean();
		checkAnswersBean.setAd(ad);
		
		CheckAnswersController controllerAnswers = CheckAnswersController.getInstance();
		controllerAnswers.answersList(checkAnswersBean);
		
		List<Answer> answers = checkAnswersBean.getAnswersList();
		
		List<String> storedUsernames = new ArrayList<>();
		for(int i = 0; i < answers.size(); i++) {
			System.out.println("CANDIDATO " + i + " : " + answers.get(i).getUsername());
			storedUsernames.add(answers.get(i).getUsername());
		}
		Assert.assertTrue(storedUsernames.containsAll(Arrays.asList(candidati)));
 	}

}
