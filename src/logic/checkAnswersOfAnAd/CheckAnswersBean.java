package logic.checkAnswersOfAnAd;

public class CheckAnswersBean {
	
	private static CheckAnswersBean instance;
	private int id;
	
	public static CheckAnswersBean getInstance() {
		if(instance == null)
			instance = new CheckAnswersBean();
		return instance;
	}
	
	private CheckAnswersBean() {	
		this.id = -1;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	
}
