package test.reviews;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import logic.beans.KeyReviewsBean;
import logic.beans.ReviewsBean;
import logic.reviews.ReviewsController;

public class TestWriteReview {

	@Test
	public void WriteReviewController() {
		
		ReviewsBean reviewsBean = new ReviewsBean();
		
		reviewsBean.setUsernameToReview("Pluto");
		reviewsBean.setUsernameWriter("Pippo");
		reviewsBean.setVote(4);
		reviewsBean.setText("Mi è sembrato molto professionale mettendo in primo piano il mio soddisfacimento del lavoro svolto");
	
		ReviewsController controller = ReviewsController.getInstance();
		
		controller.writeReview(reviewsBean);
		
		File folder = new File("db/serialized/Review");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++) {
			Assert.assertEquals(true, listOfFiles[i].getName().contains(reviewsBean.getAttributes().get((Object)KeyReviewsBean.REVIEWSBEAN_USERNAMETOREVIEW) + "#" + reviewsBean.getAttributes().get((Object)KeyReviewsBean.REVIEWSBEAN_USERNAMEWRITER)));
		}
	}

}
