package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class CurriculumMainPageCompononent implements ViewComponent{
	 Logger loggerCMP = Logger.getLogger(this.getClass().getName());

		
		@Override
		public Node buildComponent() {
		String toPrintCVM = "In curriculumMainPageComponenet ";
		String pathCVM = "src/logic/view/fxml_curriculummain.fxml";
			FXMLLoader curriculumMainPageLoader = new FXMLLoader();
			ScrollPane curriculumMainPage = new ScrollPane();
			
			curriculumMainPageLoader.setRoot(curriculumMainPage);
			
			try (FileInputStream src = new FileInputStream(pathCVM)){
				curriculumMainPageLoader.load(src);
			} catch (IOException e) {
				loggerCMP.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintCVM, e.toString()) );

				return null;
			} 
			
			return curriculumMainPage;
		}
}
