// Jianu Mihai
package test.answers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import logic.answeranad.AnswerAnAdController;
import logic.answeranad.UserAlreadyAnsweredException;
import logic.beans.AnswerAnAdBean;
import logic.beans.CheckAnswersBean;
import logic.checkanswersofanad.CheckAnswersController;
import logic.entity.Ad;
import logic.entity.RequestAd;


public class TestAnswers {

	@Test
	public void testAnswers() throws IllegalAccessException, UserAlreadyAnsweredException {
		String[] candidati = new String[3];
		candidati[0] = "Pippo";
		candidati[1] = "Pluto";
		candidati[2] = "Paperino";
		
		int idAd = 1;  
		String typeAd = "RequestAd";
		
		for(int i = 0; i < candidati.length; i++) {
			File deliteFile =  new File("db/serialized/Answer/" + idAd + "#" + candidati[i] + "#" + typeAd + "#.ser");
			deliteFile.delete();
		}
		
		AnswerAnAdBean answerAnAdBean = new AnswerAnAdBean();
		
		answerAnAdBean.setId(idAd);
		answerAnAdBean.setType(typeAd);
		
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
		checkAnswersBean.setAdId(ad.getId());
		checkAnswersBean.setAdType(ad.getClass().getSimpleName());
		
		CheckAnswersController controllerAnswers = CheckAnswersController.getInstance();
		controllerAnswers.answersList(checkAnswersBean);
		
		
		List<String> storedUsernames = new ArrayList<>();
		for(int i = 0; i < checkAnswersBean.getNumOfAnswers(); i++) {
			System.out.println("CANDIDATO " + i + " : " + checkAnswersBean.getAnswerer(i));
			storedUsernames.add(checkAnswersBean.getAnswerer(i));
		}
		Assert.assertTrue(storedUsernames.containsAll(Arrays.asList(candidati)));
 	}

}
