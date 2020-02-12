package logic.view;


public abstract class View {
    
	private String next;
	
	public void setNext(String next) {
		this.next = next;
	}
	
	public String getNext() {
		return this.next;
	}
		
	
    private String fxmlpath;
    
    public String getFXMLPath() {
    	return this.fxmlpath;
    }
    
    public void setFXMLPath(String fxmlpath) {
    	this.fxmlpath = fxmlpath;
    }

	
    private View previous;
	
	public void setPrevious(View previous) {
		this.previous = previous;
	}
	
	public View getPrevious() {
		return this.previous;
	}
	
	
    //This method will allow the injection of the Parent ScreenPane
    public abstract void goNext(String viewName);
}

