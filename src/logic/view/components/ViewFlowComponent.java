package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;


public class ViewFlowComponent implements ViewComponent {

	 Logger loggerF = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintVF = "In ViewFlowComponenet";
		String pathVF ="bin/logic/view/fxml_flow.fxml";
		FXMLLoader viewFlowLoader = new FXMLLoader();
		ScrollPane viewFlow = new ScrollPane();
		
		viewFlowLoader.setRoot(viewFlow);
		
		try (FileInputStream src = new FileInputStream(pathVF)){
			viewFlowLoader.load(src);
		} catch (IOException e) {
        	loggerF.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintVF, e.toString()));

			return null;
		} 
		
		return viewFlow;
	}

}
