package logic.entity;

public class AdFactory {

	public Ad typePost (String type) {
		switch (type) {
			case "Richiesta" : return new RequestAd();
			case "Offerta": return new OfferAd();
			default: return null;
		}
	}
	
	public Ad createRequestAd() {
		return new RequestAd();
	}
	
	public Ad createOfferAd() {
		return new OfferAd();
	}
}
