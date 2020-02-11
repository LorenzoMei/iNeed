package logic.entity;

public class AdFactory {

	private static AdFactory ref = null;
	
	public static AdFactory getReference(){
		if(ref == null)
			ref = new AdFactory();
		return ref;
	}
	
	private AdFactory() {}
	
	public Ad typePost (String type) {
		switch (type) {
			case "Richiesta" : return new RequestAd();
			case "Offerta": return new OfferAd();
			default: return null;
		}
	}
}
