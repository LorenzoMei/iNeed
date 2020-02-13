package logic.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMap  extends View implements Initializable, MapComponentInitializedListener {

	//will gonna be used when a position will be inserted
    //private double lat;
    //private double lon;
	@FXML private Text tester;

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
    	tester.setText("User");

		
    }

    @FXML protected void handleSubmitButtonViewProfile(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewUser");

	    }
	 
	 @FXML protected void handleSubmitButtonExit(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewLogin");

	    }
	 
	 @FXML protected void handleSubmitButtonMakeAnOffer(ActionEvent event) {
		 tester.setText("MakeAnOffer");

	 }
	 
	 @FXML protected void handleSubmitButtonMakeARequest(ActionEvent event) {
		 tester.setText("MakeARequest");
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
