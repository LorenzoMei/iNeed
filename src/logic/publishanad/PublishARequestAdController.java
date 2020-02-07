package logic.publishanad;

import logic.dao.DAOPost;

public class PublishARequestAdController implements PublishAnAdInterface{
	
	private static PublishARequestAdController instance;
	PublishAnAdBean bean = PublishAnAdBean.getInstance();
	
	public static PublishARequestAdController getInstance() {
		if(instance == null)
			instance = new PublishARequestAdController();
		return instance;
	}
	
	private PublishARequestAdController() {
	}
	
	public void createPost() {
		String title = bean.getTitle();
		String body = bean.getBody();
		String type = "Richiesta";
		
		DAOPost post = new DAOPost();
		post.createPost(title, body, type);		
	}
}
