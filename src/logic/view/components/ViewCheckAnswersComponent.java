package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class ViewCheckAnswersComponent implements ViewComponent {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public Node buildComponent() {
		String path =FXMLPaths.CHECKANSWERS.getPath();
		FXMLLoader checkAnswersLoader = new FXMLLoader();
		ScrollPane checkAnswers = new ScrollPane();
		
		checkAnswersLoader.setRoot(checkAnswers);
		
		try (FileInputStream src = new FileInputStream(path)){
			checkAnswersLoader.load(src);
		} catch (IOException e) {
        	logger.log(Level.SEVERE, String.format("The erro says:  %s", e.toString()));

			return null;
		} 
		
		return checkAnswers;
	}	
}


