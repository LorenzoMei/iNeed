package test.reviews;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import logic.beans.ReviewsBean;
import logic.reviews.ReviewsController;

public class TestReview {

	@Test
	public void ReviewController() {
		
		ReviewsBean reviewsBean = new ReviewsBean();
		ReviewsController controller = ReviewsController.getInstance();
		
		String usernameWriter[] = new String[3];
		usernameWriter[0] = "Pippo";
		usernameWriter[1] = "Paperino";
		usernameWriter[2] = "Topolino";
		
		String text[] = new String[3];
		text[0] = "Mi è sembrato molto professionale mettendo in primo piano il mio soddisfacimento del lavoro svolto";
		text[1] = "Mi è sembrato molto professionale";
		text[2] = "Un po' distaccato ma buono";
		
		Integer vote[] = new Integer[3];
		vote[0] = 4;
		vote[1] = 3;
		vote[2] = 3;
		
		reviewsBean.setUsernameToReview("Pluto");
		reviewsBean.setUsernameWriter(usernameWriter[0]);
		reviewsBean.setVote(vote[0]);
		reviewsBean.setText(text[0]);
		
		controller.writeReview(reviewsBean);
		
		reviewsBean.setUsernameWriter(usernameWriter[1]);
		reviewsBean.setVote(vote[1]);
		reviewsBean.setText(text[1]);
		
		controller.writeReview(reviewsBean);
		
		reviewsBean.setUsernameWriter(usernameWriter[2]);
		reviewsBean.setVote(vote[2]);
		reviewsBean.setText(text[2]);
		
		controller.writeReview(reviewsBean);
		
		controller.readReview(reviewsBean);
		
		List<String> allText = reviewsBean.getAllText();
		List<String> allUsername = reviewsBean.getAllUsernameWriter();
		List<Integer> allVote = reviewsBean.getAllVote();
		
		for(int i = 0; i < allText.size(); i++) {
			Assert.assertEquals(usernameWriter[i], allUsername.get(i));
			Assert.assertEquals(vote[i], allVote.get(i));
			Assert.assertEquals(text[i], allText.get(i));
		}
	}

}
