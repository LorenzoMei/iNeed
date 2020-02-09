package logic.entity;

public class AdId{

	private String name= "lastId";
	private int id;
	
	public AdId() {
		this.id = 1;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
