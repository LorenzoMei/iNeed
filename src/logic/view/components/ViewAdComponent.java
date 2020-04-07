package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class ViewAdComponent implements ViewComponent {
Logger loggerAd = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintAd = "In ViewAdComponent ";
		String pathAd = FXMLPaths.AD.getPath();
		FXMLLoader adLoader = new FXMLLoader();
		ScrollPane ad = new ScrollPane();
		
		adLoader.setRoot(ad);
		
		try (FileInputStream src = new FileInputStream(pathAd)){
			adLoader.load(src);
		} catch (IOException e) {
        	loggerAd.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintAd, e.toString()));

			return null;
		} 
		
		return ad;
	}
}
