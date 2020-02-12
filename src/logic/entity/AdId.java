package logic.entity;

public class AdId{

//	private String name;
	private int lastId;
	private String type;
	
//	public AdId() {
//		this.name = this.getClass().getSimpleName();
//	}
	
	public void setType(String val) {
		this.type = val;
	}
	
	public void setLastId(int id) {
		this.lastId = id;
	}
	
//	public void setName(String name) {
//		this.name = name;
//	}
	
	public String getType() {
		return this.type;
	}
	
	public int getLastId() {
		return this.lastId;
	}
	
//	public String getName() {
//		return this.name;
//	}
}
