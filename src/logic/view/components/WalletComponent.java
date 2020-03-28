package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class WalletComponent implements ViewComponent{
	Logger loggerW = Logger.getLogger(this.getClass().getName());
	
	@Override
	public Node buildComponent() {
		String toPrintW = "In ViewWalletComponenet";
		String pathW = "src/logic/view/fxml_wallet.fxml";
		FXMLLoader viewWalletLoader = new FXMLLoader();
		ScrollPane viewWallet = new ScrollPane();
		
		viewWalletLoader.setRoot(viewWallet);
		
		try (FileInputStream src = new FileInputStream(pathW)){
			viewWalletLoader.load(src);
		} catch (IOException e) {
       	loggerW.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintW, e.toString()));

			return null;
		} 
		
		return viewWallet;
	}
}
