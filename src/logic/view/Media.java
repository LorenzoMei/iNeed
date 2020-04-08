package logic.view;

public enum Media {
	
	DIALOG_INFO_COMPLETEDTASK ("media/icons8-completed-task-64.png"),
	DIALOG_INFO_CONTACTUSER ("media/icons8-chat-64.png"),
	;
	
	private String path;
	
	Media (String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
