package logic.view.components;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import logic.view.View;


public class ViewMapController implements Initializable, MapComponentInitializedListener{

		
		@FXML private Text testerMap;
		@FXML private MenuItem profileName;
		public View nextViewM;

	 	Logger logger = Logger.getLogger(this.getClass().getName());
	    @FXML private GoogleMapView mapView;


	    @Override
	    public void initialize(URL url, ResourceBundle resourceBundle) {
			 String status = "My location " + url + " my resoursources: " + resourceBundle;
			 logger.log(Level.INFO, status);
	         mapView.addMapInializedListener(this);
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
