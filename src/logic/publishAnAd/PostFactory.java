package logic.publishAnAd;


public class PostFactory {

	public PublishAnAdInterface typePost (int type) {
		switch (type) {
			case 1: return PublishARequestAdController.getInstance();
			case 2: return PublishAnOfferAdController.getInstance();
			default: return null;
		}
	}
	
	public PublishAnAdInterface createPublishARequestAdController() {
		return PublishARequestAdController.getInstance();
	}
	
	public PublishAnAdInterface createPublishAnOfferAdController() {
		return PublishAnOfferAdController.getInstance();
	}
}
