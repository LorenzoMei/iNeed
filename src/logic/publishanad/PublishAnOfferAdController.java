package logic.publishanad;

import logic.dao.DAOPost;

public class PublishAnOfferAdController implements PublishAnAdInterface{

	private static PublishAnOfferAdController instance;
	PublishAnAdBean bean = PublishAnAdBean.getInstance();

	public static PublishAnOfferAdController getInstance() {
		if(instance == null) 
			instance = new PublishAnOfferAdController();
		return instance;
	}
	
	private PublishAnOfferAdController() {
	}
	
	public void createPost() {
		String title = bean.getTitle();
		String body = bean.getBody();
		String type = "Offerta";
		
		DAOPost post = new DAOPost();
		post.createPost(title, body, type);
	}
}
