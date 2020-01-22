package logic.PublishARequestAd;

public class PublishARequestAdController implements PublishARequestAdInterface{
	private static PublishARequestAdController instance;
	
	PublishARequestAdBean bean = PublishARequestAdBean.getInstance();
	PostDao postDao = new PostDao();
	
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
		String type = "Request";
		postDao.createPostDao(title, body, type);
		return;
	}
}
