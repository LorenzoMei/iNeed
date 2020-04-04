package logic.entity;

public enum Ads {
	OFFER (OfferAd.class.getSimpleName()),
	REQUEST(RequestAd.class.getSimpleName())
	;
	
	private String name;

	private Ads(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
