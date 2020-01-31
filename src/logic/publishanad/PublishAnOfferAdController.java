package logic.publishanad;

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
		
		PostDao post = new PostDao();
		post.createPost(title, body, type);
		
		return;
	}
}
