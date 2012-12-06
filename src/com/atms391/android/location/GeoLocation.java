package com.atms391.android.location;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class GeoLocation {
	private Activity activity;
	private LocationManager locationManager;
	private String bestLocation;

	private double latitude;
	private double longitude;

	/////////////// CONSTRUCTORS: ///////////////
	public GeoLocation(Activity activity) throws Exception{
		this.activity = activity;

		requestLocationService();
		determineBestLocationServiceProvider();
		updateLocation(bestLocation);
	}

	/////////////// PUBLIC ACCESSORS: ///////////////
	public void updateLocation() throws Exception{
		determineBestLocationServiceProvider();
		updateLocation(bestLocation);
	}

	public String getLocationString(){
		StringBuilder stringBuilder = new StringBuilder();

		if(isLocationValid()){
			stringBuilder.append("(");
			stringBuilder.append(getLatitudePrettyPrinted());
			stringBuilder.append(", ");
			stringBuilder.append(getLongitudePrettyPrinted());
			stringBuilder.append(")");
		} else {
			stringBuilder.append("LOCATION NOT DETERMINED");
		}

		return stringBuilder.toString();
	}
	
	public String getLatitudePrettyPrinted(){
		StringBuilder stringBuilder = new StringBuilder();

		if(latitude > 0){									// In the northern hemisphere
			stringBuilder.append(latitude);
			stringBuilder.append("°");
			stringBuilder.append("N");
		} else if(latitude < 0){							// In the southern hemisphere
			stringBuilder.append(Math.abs(latitude));
			stringBuilder.append("°");
			stringBuilder.append("S");
		} else {											// At the equator
			stringBuilder.append(latitude);
			stringBuilder.append("°");
		}

		return stringBuilder.toString();
	}

	public String getLongitudePrettyPrinted(){
		StringBuilder stringBuilder = new StringBuilder();

		if(longitude > 0){									// In the eastern hemisphere
			stringBuilder.append(longitude);
			stringBuilder.append("°");
			stringBuilder.append("E");
		} else if(longitude < 0){							// In the western hemisphere
			stringBuilder.append(Math.abs(longitude));
			stringBuilder.append("°");
			stringBuilder.append("W");
		} else {											// At the prime meridian
			stringBuilder.append(longitude);
			stringBuilder.append("°");
		}

		return stringBuilder.toString();
	}

	/////////////// PRIVATE HELPERS: ///////////////
	private void requestLocationService(){
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
	}

	private void determineBestLocationServiceProvider(){
		Criteria criteria = new Criteria();
		bestLocation = locationManager.getBestProvider(criteria, false);
	}

	private void updateLocation(String bestProvider) throws Exception {
		Location location = locationManager.getLastKnownLocation(bestProvider);

		if(location == null){
			Toast.makeText(activity, "Location Not Found", Toast.LENGTH_LONG).show();
		} else {
			Geocoder geocoder = new Geocoder(activity);
			List<Address> user = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

			latitude = (double) user.get(0).getLatitude();
			longitude = (double) user.get(0).getLongitude();
		}
	}

	private boolean isLocationValid(){
		boolean isValid = true;
		if(latitude > 181 || latitude < -181){
			isValid = false;
		}
		if(longitude > 181 || longitude < -181){
			isValid = false;
		}

		return isValid;
	}

	/////////////// PUBLIC GETTERS: /////////////// 
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getBestLocationMethodString(){
		return bestLocation;
	}
	
	public boolean usesGps(){
		if(bestLocation.equalsIgnoreCase("gps")){
			return true;
		} else {
			return false;
		}
	}
	
	public String usesGpsString(){
		if(bestLocation.equalsIgnoreCase("gps")){
			return "TRUE";
		} else {
			return "FALSE";
		}
	}

	/////////////// PUBLIC SETTERS: ///////////////
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
