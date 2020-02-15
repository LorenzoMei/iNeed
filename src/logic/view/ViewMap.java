package logic.view;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import logic.beans.LogoutBean;
import logic.beans.ViewProfileBean;
import logic.login.LoginController;
import logic.viewprofile.ViewProfileController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMap  extends View implements Initializable, MapComponentInitializedListener {

	//TODO : will gonna be used when a position will be inserted
    
	//private double lat;
    //private double lon;
	
	@FXML private Text testerMap;
	@FXML private MenuItem profileName;
	ViewProfileBean pBean = new ViewProfileBean();
 	ViewProfileController pController =  ViewProfileController.getInstance();
 	LoginController loginController =  LoginController.getInstance();
	LogoutBean lBean = new LogoutBean();
	
 	Logger logger = Logger.getLogger(this.getClass().getName());
    @FXML private GoogleMapView mapView;

	public  ViewMap() {	    	
	    	this.setFXMLPath("fxml_map.fxml");
	    	pBean.setRequestedUsername(View.getProfileName());
			pController.loadProfile(pBean);

	    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		 profileName.setText(View.getProfileName());
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
         mapView.addMapInializedListener(this);
    }
    
    @FXML protected void handleSubmitButtonViewFlowM(ActionEvent event) {
   	 Context.getReference().goNext(GoNextTargets.VIEW_FLOW.getStateName());
    }
    
    @FXML protected void handleSubmitButtonUserM(ActionEvent event) {
    	testerMap.setText("User");

		
    }

    @FXML protected void handleSubmitButtonViewProfileM(ActionEvent event) {
 		Context.getReference().goNext(GoNextTargets.VIEW_USER.getStateName());

	    }
	 
	 @FXML protected void handleSubmitButtonExitM(ActionEvent event) {
		testerMap.setText("LoggingOut");
		lBean.setUsername(View.getProfileName());
	 	loginController.logout(lBean);
 		Context.getReference().goNext(GoNextTargets.VIEW_LOGIN.getStateName());

	    }
	 @FXML protected void handleSubmitButtonViewMapM(ActionEvent event) {
		 testerMap.setText("");
	 	Context.getReference().goNext(GoNextTargets.VIEW_MAP.getStateName());

		}
	 
	 @FXML protected void handleSubmitButtonMakeAnAdM(ActionEvent event) {
		 testerMap.setText("MakeAnAd");
		 Context.getReference().goNext(GoNextTargets.VIEW_MAKEANAD.getStateName());

	 }
	 	 
	 @FXML protected void handleSubmitButtonValidateAFavorM(ActionEvent event) {
		 testerMap.setText("ValidateAFavor");
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
