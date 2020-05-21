package test.web;

public enum XPaths {
	LOGIN_BLOGIN("//*[@id=\"login\"]"),
	LOGIN_FUSERNAME("//*[@id=\"username\"]"),
	LOGIN_FPASSWORD("//*[@id=\"password\"]"),
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
