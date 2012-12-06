package com.atms391.android;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.atms391.android.constants.Constants;
import com.atms391.android.equations.angle.HourAngle;
import com.atms391.android.equations.angle.SolarAltitudeAngle;
import com.atms391.android.equations.angle.SolarAzimuthAngle;
import com.atms391.android.equations.angle.SolarDeclination;
import com.atms391.android.equations.angle.SolarIncidenceAngle;
import com.atms391.android.equations.atmosphere.AirMassRatio;
import com.atms391.android.equations.atmosphere.AtmosphericOpticalDepth;
import com.atms391.android.equations.atmosphere.SkyDiffuseFactor;
import com.atms391.android.equations.helpers.TimeHelper;
import com.atms391.android.equations.insolation.ApparentExtraterrestrialSolarInsolation;
import com.atms391.android.equations.insolation.BeamInsolationAtEarthsSurface;
import com.atms391.android.equations.insolation.BeamInsolationOnCollector_Ibc;
import com.atms391.android.equations.insolation.DiffuseInsolation_Idc;
import com.atms391.android.equations.insolation.ReflectedInsolationOnCollector_Irc;
import com.atms391.android.equations.insolation.SolarInsolation_Ic;
import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.location.LocationHelper;

public class MainActivity extends Activity implements SensorEventListener{
	// CONTROL VARIABLES:
	private boolean captureData;
	private LocationManager locationManager = null;
	private SensorManager sensorManager;
	private Sensor rotation;

	// GUI ELEMENTS:
	private Switch captureSensorDataSwitch;
	private TextView locationDataTextView;
	private TextView usesGpsDataTextView;
	private TextView azimuthDataTextView;
	private TextView collectorTiltAngleDataTextView;
	private TextView solarInsolationDataTextView;
	private EditText panelAreaEditText;
	private EditText panelEfficiecyEditText;
	
	// VALUES:
	private double userInputtedPanelArea = 0.00;
	private double userInputtedPanelEfficiency = 0.00;
	private double latitude = 0;
	private double longitude = 0;
	private double collectorTiltAngle = 0;
	private double compassDirection = 0;
	private double collectorInsolation = 0.00;

	/////////////// ACTIVITY METHODS: ///////////////
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeGui();
		configureGui();
		initializeLocationFinderService();
		initializeSensorReader();
		addListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		registerLocationListeners();
		registerSensorListeners();
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterLocationListeners();
		unregisterSensorListener();
	}

	/////////////// PRIVATE HELPER FUNCTIONS: ///////////////
	/**
	 * Initializes GUI elements.
	 */
	private void initializeGui(){
		// Grab GUI elements from manifest:
		captureSensorDataSwitch = (Switch) findViewById(R.id.captureSensorDataSwitch);
		locationDataTextView = (TextView) findViewById(R.id.locationDataTextView);
		usesGpsDataTextView = (TextView) findViewById(R.id.usesGpsDataTextView);
		azimuthDataTextView = (TextView) findViewById(R.id.azimtuhDataTextView);
		collectorTiltAngleDataTextView = (TextView) findViewById(R.id.collectorTiltAngleDataTextView);
		solarInsolationDataTextView = (TextView) findViewById(R.id.solarInsolationDataTextView);
		panelAreaEditText = (EditText) findViewById(R.id.panelAreaEditText);
		panelEfficiecyEditText = (EditText) findViewById(R.id.panelEfficiecyEditText);
	}

	private void configureGui(){
		// Set capture switch to on:
		captureSensorDataSwitch.setChecked(true);
		captureData = true;

		// Default area and efficiency values to zero:
		panelAreaEditText.setText("0");
		panelEfficiecyEditText.setText("0");

		// Application configurations:
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/**
	 * Initializes and registers service listeners.
	 */
	private void addListeners(){
		captureSensorDataSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
				captureData = isChecked;
			}
		});

		panelAreaEditText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				String panelAreaAsString = s.toString();
				if(!panelAreaAsString.isEmpty()){
					userInputtedPanelArea = Double.parseDouble(panelAreaAsString);
					Log.d("PanelArea", new StringBuilder().append(userInputtedPanelArea).toString());
				} else {
					userInputtedPanelArea = 0;
				}

				calculateCollectorInsolation();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

		});
		
		panelEfficiecyEditText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				String panelEfficiencyAsString = s.toString();
				if(!panelEfficiencyAsString.isEmpty()){
					userInputtedPanelEfficiency = Double.parseDouble(panelEfficiencyAsString);
					Log.d("PanelEfficiency", new StringBuilder().append(userInputtedPanelEfficiency).toString());
				} else {
					userInputtedPanelEfficiency = 0;
				}

				calculateCollectorInsolation();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
		});

		registerSensorListeners();
	}
	
	// TODO DO THIS!
	private double calculateCollectorInsolation(){
		int dayNumber = TimeHelper.getTodaysDayNumber() + 1;
		Calendar clockTime = Calendar.getInstance();
		
		double totalSolarInsolationOnCollectorInWattsPerMeter = SolarInsolation_Ic.getSolarInsolationOnCollector(dayNumber, clockTime, longitude, latitude, compassDirection, collectorTiltAngle);
		if(totalSolarInsolationOnCollectorInWattsPerMeter < 0){
			totalSolarInsolationOnCollectorInWattsPerMeter = 0;
		}
		
		solarInsolationDataTextView.setText(new StringBuilder().append(totalSolarInsolationOnCollectorInWattsPerMeter).append(" W/m^2").toString());
		
		return totalSolarInsolationOnCollectorInWattsPerMeter;
	}

	/////////////// SENSOR HANDLING FUNCTIONS: ///////////////
	private void initializeSensorReader(){
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		registerSensorListeners();
	}

	private void registerSensorListeners(){
		sensorManager.registerListener(this,  rotation, SensorManager.SENSOR_DELAY_UI);
	}

	private void unregisterSensorListener(){
		sensorManager.unregisterListener(this, rotation);
	}

	private void updateSensor(float collectorTiltAngle, float azimuthAngle){
		if(captureData){
			__updateSensorData(Math.abs(collectorTiltAngle), azimuthAngle);
			calculateCollectorInsolation();
		}
	}

	private void __updateSensorData(float collectorTiltAngle, float azimuthAngle){
		String azimuthAngleString = new StringBuilder().append(azimuthAngle).toString();
		String collectorTiltAngleString = new StringBuilder().append(collectorTiltAngle).toString();

		azimuthDataTextView.setText(azimuthAngleString);
		collectorTiltAngleDataTextView.setText(collectorTiltAngleString);
		
		this.collectorTiltAngle = collectorTiltAngle;
		this.compassDirection = azimuthAngle;
		
		// TODO REMOVE THIS DEBUGGINC CODE:
		Log.d("TiltAngle", new StringBuilder().append(this.collectorTiltAngle).toString());
		Log.d("CompassHeading", new StringBuilder().append(this.compassDirection).toString());
		////
	}

	/////////////// LOCATION HANDLING FUNCTIONS: ///////////////
	private void initializeLocationFinderService(){
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		registerLocationListeners();
	}

	private void registerLocationListeners(){
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3600000, 1000, locationListener);
	}

	private void unregisterLocationListeners(){
		locationManager.removeUpdates(locationListener);
	}

	/**
	 * Takes current location, pretty-ifies the latitude and longitude values
	 * and prints those values to the main GUI.
	 * @param currentLocation	current location from LOCATION_PROVIDER
	 */
	private void updateLocation(Location currentLocation){
		if(captureData){
			__updateLocationData(currentLocation.getLatitude(), currentLocation.getLongitude());
			__updateLocationProvider(currentLocation.getProvider());
		}
	}

	private void __updateLocationData(double latitude, double longitude){
		StringBuilder locationString = new StringBuilder();

		locationString.append("(");
		locationString.append(LocationHelper.getPrettyPrintedLatitude(latitude));
		locationString.append(", ");
		locationString.append(LocationHelper.getPrettyPrintedLongitude(longitude));
		locationString.append(")");

		locationDataTextView.setText(locationString.toString());
		this.latitude = latitude;
		this.longitude = longitude;
		
		// TODO REMOVE THIS DEBUGGING CODE
		Log.d("Latitude", new StringBuilder().append(this.latitude).toString());
		Log.d("Longitude", new StringBuilder().append(this.longitude).toString());
		////
	}

	private void __updateLocationProvider(String provider){
		if(provider.equalsIgnoreCase("gps")){
			usesGpsDataTextView.setText("TRUE");
		} else {
			usesGpsDataTextView.setText("FALSE");
		}
	}

	/////////////// LISTENERS: ///////////////
	public LocationListener locationListener = new LocationListener(){
		/**
		 * LocationListener::onLocationChanged(...)
		 * called whenever the LOCATION_SERVICE's location changes
		 */
		@Override
		public void onLocationChanged(Location location){
			updateLocation(location);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){}

		@Override
		public void onProviderEnabled(String provider){}

		@Override
		public void onProviderDisabled(String provider){}
	};

	/**
	 * SensorEventListener::onAccuracyChanged(..)
	 * @param sensor	sensor type
	 * @param accuracy	accuracy
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){}

	/**
	 * SensorEventListener::onSensorChanged(...)
	 * This method is called whenever the phone's sensor's readings are polled.
	 * @param event		sensor change event
	 */
	@Override
	public void onSensorChanged(SensorEvent event){
		float[] values = event.values.clone();

		Log.d("SENSOR", "SENSOR CHANGED");

		if(values != null){
			int phoneOrientation = getResources().getConfiguration().orientation;
			float tiltAngle, azimuthAngle;

			switch(phoneOrientation){
			case Configuration.ORIENTATION_LANDSCAPE:
				tiltAngle = values[2];
				azimuthAngle = values[0];
				break;
			case Configuration.ORIENTATION_PORTRAIT:
				tiltAngle = values[1];
				azimuthAngle = values[0];
				break;
			default:
				tiltAngle = values[2];
				azimuthAngle = values[0];
				break;
			}

			updateSensor(tiltAngle, azimuthAngle);	
		}
	}
}
