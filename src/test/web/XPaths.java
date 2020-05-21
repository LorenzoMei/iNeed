package test.web;

public enum XPaths {
	CHECKANSWERS_CONTACTINFO("//*[@id=\"contactInfo\"]"),
	LOGIN_BLOGIN("//*[@id=\"login\"]"),
	LOGIN_FPASSWORD("//*[@id=\"password\"]"),
	LOGIN_FUSERNAME("//*[@id=\"username\"]"),
	VIEWAD_BCHECKANSWERS("/html/body/div[2]/div[4]/a/button"),
	VIEWAD_LOWNER("/html/body/div[2]/div[3]/div[1]/div/div[2]/span");
	
	;
	private final String xpath;
	private XPaths(String xpath) {
		this.xpath = xpath;
	}
	@Override
	public String toString() {
		return this.xpath;
	}
}
