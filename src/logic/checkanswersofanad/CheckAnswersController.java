package logic.checkanswersofanad;

public class CheckAnswersController implements CheckAnswersControllerInterface{
	
	private static CheckAnswersController instance;
	
	public static CheckAnswersController getInstance() {
		if(instance == null) {
			instance = new CheckAnswersController();
		}
		return instance;
	}
	
	private CheckAnswersController() {
	}
	
	public Answers answersList(){
		CheckAnswersBean bean = CheckAnswersBean.getInstance();
		int id = bean.getId();
		AnswersDao dao = new AnswersDao();
		return  dao.listOfAnswers(id);
	}
}
