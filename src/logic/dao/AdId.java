package logic.dao;

public class AdId{

	private int lastId;
	private String type;
	
	public void setType(String val) {
		this.type = val;
	}
	
	public void setLastId(int id) {
		this.lastId = id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getLastId() {
		return this.lastId;
	}
}
