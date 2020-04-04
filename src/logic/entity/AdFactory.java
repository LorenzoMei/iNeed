package logic.entity;

public class AdFactory {

	private static AdFactory ref = null;
	
	public static AdFactory getReference(){
		if(ref == null)
			ref = new AdFactory();
		return ref;
	}
	
	private AdFactory() {}
	
	public Ad typePost (Ads adType) {
		switch (adType) {
			case REQUEST: return new RequestAd();
			case OFFER: return new OfferAd();
			default: return null;
		}
	}
}
