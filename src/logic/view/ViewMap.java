package logic.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMap  extends View implements Initializable, MapComponentInitializedListener {

    private GoogleMap map;
    //private double lat;
    //private double lon;
 	Logger logger = Logger.getLogger(this.getClass().getName());
    @FXML private GoogleMapView mapView;

	public  ViewMap() {	    	
	    	this.setFXMLPath("fxml_map.fxml");
	    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapView.addMapInializedListener(this);
    }
    
    @FXML protected void handleSubmitButtonViewFlow(ActionEvent event) {
   	 Context.getReference().goNext("logic.view.ViewFlow");
    }
    
    @FXML protected void handleSubmitButtonUser(ActionEvent event) {
		
    }

    @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewUser");

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewLogin");

	    }
	 
	 @FXML protected void handleSubmitButtonMakeAnOffer(ActionEvent event) {
 		

	 }
	 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
			
	 }
	 
	
	 
	 @FXML protected void handleSubmitButtonValidateAFavor(ActionEvent event) {
			
	    }
	 
    @Override
    public void mapInitialized() {
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
