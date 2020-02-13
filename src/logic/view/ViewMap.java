package logic.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import logic.entity.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMap  extends View implements Initializable, MapComponentInitializedListener {

	//TODO : will gonna be used when a position will be inserted
    
	//private double lat;
    //private double lon;
	
	@FXML private Text tester;
	private final static String GOTOLOGIN = "logic.view.ViewLogin";
	private final static String GOTOUSER = "logic.view.ViewUser";
	private final static String GOTOMAP = "logic.view.ViewMap";
	private final static String GOTOFLOW = "logic.view.ViewFlow";
	private final static String GOTOMAKEANAD = "logic.view.ViewMakeAnAd";
	@FXML private MenuItem profileName;
	private User activeUser;
	
 	Logger logger = Logger.getLogger(this.getClass().getName());
    @FXML private GoogleMapView mapView;

	public  ViewMap() {	    	
	    	this.setFXMLPath("fxml_map.fxml");
	    	activeUser = View.getActiveUser();

	    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		 profileName.setText(activeUser.getUsername());
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.SEVERE, status);
         mapView.addMapInializedListener(this);
    }
    
    @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
   	 Context.getReference().goNext(GOTOFLOW);
    }
    
    @FXML protected void handleSubmitButtonUser(ActionEvent event) {
    	tester.setText("User");

		
    }

    @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
 		Context.getReference().goNext(GOTOUSER);

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext(GOTOLOGIN);

	    }
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
		 tester.setText("");
	 	Context.getReference().goNext(GOTOMAP);

		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAd(ActionEvent event) {
		 tester.setText("MakeAnAd");
		 Context.getReference().goNext(GOTOMAKEANAD);

	 }
	 

	 
	
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
		 tester.setText("ValidateAFavor");
	    }
	 
    @Override
    public void mapInitialized() {
        GoogleMap map;
		logger.log(Level.SEVERE,"Load the map" );
        LatLong latLong = new LatLong(41.9102415, 12.3959136);
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(latLong)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLong);
        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>ROMA</h2>Location di Roma<br>");
        InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
        infoWindow.open(map, marker);
    }

}
