package logic.view;

public enum Media {
	
	LOGO ("/media/Red-Logomark.png"),
	DIALOG_INFO_COMPLETEDTASK ("media/icons8-completed-task-64.png"),
	DIALOG_INFO_CONTACTUSER ("media/icons8-chat-64.png"),
	DIALOG_INFO_WIP("media/icons8-construction-26.png"),
	;
	
	private String path;
	
	Media (String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	@Override
	public String toString() {
		return this.path;
	}
}
