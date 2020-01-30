package logic.publishanad;

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
		
		PostDao post = new PostDao();
		post.createPost(title, body, type);
		
		return;
	}
}
