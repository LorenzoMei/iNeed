package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class CurriculumMainPageCompononent extends ViewComponent{
	 Logger loggerCMP = Logger.getLogger(this.getClass().getName());

		
		@Override
		public Node buildComponent() {
		
			FXMLLoader curriculumMainPageLoader = new FXMLLoader();
			ScrollPane curriculumMainPage = new ScrollPane();
			
			curriculumMainPageLoader.setRoot(curriculumMainPage);
			
			try (FileInputStream src = new FileInputStream("src/logic/view/fxml_curriculummain.fxml")){
				curriculumMainPageLoader.load(src);
			} catch (IOException e) {
				loggerCMP.log(Level.SEVERE,"In curriculumMainPageComponenet " + e.toString());

				return null;
			} 
			
			return curriculumMainPage;
		}
}
