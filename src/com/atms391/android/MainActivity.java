package com.atms391.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.atms391.android.equations.helpers.DateHelper;
import com.atms391.android.equations.helpers.LocationHelper;
import com.atms391.android.equations.helpers.NumberPrinterHelper;
import com.atms391.android.gui.tabs.DetailsTabFragment;
import com.atms391.android.gui.tabs.EnergyTabFragment;
import com.atms391.android.gui.tabs.InsolationTabFragment;
import com.atms391.android.gui.tabs.UserInputTabFragment;
import com.atms391.android.gui.tabs.framework.PagerAdapter;
import com.atms391.android.gui.tabs.framework.TabFactory;
import com.atms391.android.gui.tabs.framework.TabInfo;
import com.atms391.android.listners.OnCaptureToggleButtonChangedListener;
import com.atms391.android.listners.OnUserInputChangedListener;

public class MainActivity extends FragmentActivity implements	OnTabChangeListener, 
																OnPageChangeListener,
																SensorEventListener,
																OnUserInputChangedListener,
																OnCaptureToggleButtonChangedListener {
	
	private TabHost mTabHost;
	private ViewPager viewPager;
	private HashMap<String, TabInfo> tabInfoMap = new HashMap<String, TabInfo>(4);
	private TabInfo mLastTab = null;
	private PagerAdapter pagerAdapter;

	// Service Stuff:
	private LocationManager locationManager;
	private SensorManager sensorManager;
	private Sensor rotation;

	// Data:
	private boolean updateSensorAndLocationValues = true;
	private double latitude;
	private double longitude;
	private double panelArea;
	private double panelEfficiency;
	private boolean useUserInputtedDate = false;
	private boolean useUserInputtedClockTime = false;
	private Calendar dateAndTime = Calendar.getInstance();
	private double collectorTiltAngle;
	private double collectorCompassHeading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_viewpager_layout);

		initializeTabHost(savedInstanceState);
		if(savedInstanceState != null){
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

		initializeViewPager();
		addListeners();

		// Open to first page
		onTabChanged("inputTab");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		registerLocationListener();
		registerSensorListeners();

		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterLocationListener();
		unregisterSensorListeners();

		super.onPause();
	}

	/////////////// Private helper functions: ///////////////
	private void initializeTabHost(Bundle args){
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		TabInfo tabInfo = null;

		// Add UserInputTab:
		tabInfo = new TabInfo("inputTab", UserInputTabFragment.class, args);
		addTab(this, mTabHost, mTabHost.newTabSpec("inputTab").setIndicator("Input"), tabInfo);
		tabInfoMap.put(tabInfo.getTag(), tabInfo);

		// Add InsolationTab:
		tabInfo = new TabInfo("insolationTab", InsolationTabFragment.class, args);
		addTab(this, mTabHost, mTabHost.newTabSpec("insolationTab").setIndicator("Insolation"), tabInfo);
		tabInfoMap.put(tabInfo.getTag(), tabInfo);

		// Add EnergyTab:
		tabInfo = new TabInfo("energyTab", EnergyTabFragment.class, args);
		addTab(this, mTabHost, mTabHost.newTabSpec("energyTab").setIndicator("Energy"), tabInfo);
		tabInfoMap.put(tabInfo.getTag(), tabInfo);

		// Add DetailsTab:
		tabInfo = new TabInfo("detailsTab", DetailsTabFragment.class, args);
		addTab(this, mTabHost, mTabHost.newTabSpec("detailsTab").setIndicator("Details"), tabInfo);
		tabInfoMap.put(tabInfo.getTag(), tabInfo);
	}

	private void addTab(MainActivity activity, TabHost tabHost, TabSpec tabSpec, TabInfo tabInfo){
		tabSpec.setContent(new TabFactory(activity));
		tabHost.addTab(tabSpec);

		String tag = tabSpec.getTag();
		tabInfo.setFragment(activity.getSupportFragmentManager().findFragmentByTag(tag));
		if(tabInfo.getFragment() != null && !tabInfo.getFragment().isDetached()){
			FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			ft.detach(tabInfo.getFragment());
			ft.commit();
			activity.getSupportFragmentManager().executePendingTransactions();
		}
	}

	private void initializeViewPager(){
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, UserInputTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, InsolationTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, EnergyTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, DetailsTabFragment.class.getName()));
		pagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);

		viewPager = (ViewPager) super.findViewById(R.id.viewpager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(this);
	}

	private void addListeners(){
		mTabHost.setOnTabChangedListener(this);

		initializeLocaitonFinderService();
		initializeSensorReader();
	}

	private void initializeLocaitonFinderService(){
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		registerLocationListener();
	}

	private void registerLocationListener() {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3600000, 1000, locationListener);
	}

	private void unregisterLocationListener(){
		locationManager.removeUpdates(locationListener);
	}
	
	private void updateDateOrTime(){
		if(!useUserInputtedDate){
			int hour = dateAndTime.get(Calendar.HOUR_OF_DAY);
			int minute = dateAndTime.get(Calendar.MINUTE);
			int second = dateAndTime.get(Calendar.SECOND);
			
			dateAndTime = Calendar.getInstance();
			dateAndTime.set(Calendar.HOUR_OF_DAY, hour);
			dateAndTime.set(Calendar.MINUTE, minute);
			dateAndTime.set(Calendar.SECOND, second);
		}
		
		if(!useUserInputtedClockTime){
			int month = dateAndTime.get(Calendar.MONTH);
			int dayOfMonth = dateAndTime.get(Calendar.DAY_OF_MONTH);
			int year = dateAndTime.get(Calendar.YEAR);
			
			dateAndTime = Calendar.getInstance();
			
			dateAndTime.set(Calendar.MONTH, month);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			dateAndTime.set(Calendar.YEAR, year);
		}
	}

	/////////////// LOCATION LISTNER: ///////////////
	public LocationListener locationListener = new LocationListener(){

		@Override
		public void onLocationChanged(Location location) {
			if(updateSensorAndLocationValues){
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				
				// TODO SEND THIS DATA OFF!
				Log.d("MainActivity", "Lat: " + String.valueOf(latitude) + "\t\tLong: " + String.valueOf(longitude));
				sendDataToDetailsTab();
			}
		}

		@Override
		public void onProviderDisabled(String provider) {}

		@Override
		public void onProviderEnabled(String provider) {}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}

	};

	/////////////// SENSOR LISTNERS: ///////////////
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values.clone();

		float tiltAngle = -1, azimuthAngle = -1;
		if(values != null){
			int phoneOrientation = getResources().getConfiguration().orientation;

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
			
			if(updateSensorAndLocationValues){
				collectorTiltAngle = tiltAngle;
				collectorCompassHeading = azimuthAngle;
				
				// TODO SEND THIS DATA OFF!
				sendDataToDetailsTab();
			}
		}
	}

	private void initializeSensorReader(){
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}

	private void registerSensorListeners(){
		sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_UI);
	}

	private void unregisterSensorListeners(){
		sensorManager.unregisterListener(this, rotation);
	}

	/////////////// OnTabChangeListener LISTENER: ///////////////
	@Override
	public void onTabChanged(String tabId) {
		TabInfo newTab = tabInfoMap.get(tabId);
		if (mLastTab != newTab) {
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
			if (mLastTab != null) {
				if (mLastTab.getFragment() != null) {
					ft.detach(mLastTab.getFragment());
				}
			}
			if (newTab != null) {
				if (newTab.getFragment() == null) {
					newTab.setFragment(Fragment.instantiate(this, newTab.getClss().getName(), newTab.getArgs()));
					ft.add(R.id.viewpager, newTab.getFragment(), newTab.getTag());
				} else {
					ft.attach(newTab.getFragment());
				}
			}

			mLastTab = newTab;
			ft.commit();
			this.getSupportFragmentManager().executePendingTransactions();
		}

		int position = mTabHost.getCurrentTab();
		viewPager.setCurrentItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {}

	@Override
	public void onPageScrolled(int position, float positoinOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {
		mTabHost.setCurrentTab(position);
	}

	/////////////// OnUserInputChanged Listener: ///////////////
	@Override
	public void onPanelAreaChanged(String newPanelAreaString) {
		Log.d("UserInputPanelArea", newPanelAreaString);
	}

	@Override
	public void onPanelEfficiencyChanged(String newPanelEfficiencyString) {
		Log.d("UserInputPanelEfficiency", newPanelEfficiencyString);
	}

	// TODO TEST THIS LOGIC
	@Override
	public void onDateChanged(String newDate) {
		Log.d("UserInputDate", newDate);
		
		if(updateSensorAndLocationValues){
			if(DateHelper.isDateStringValid(newDate)){
				useUserInputtedDate = true;
				Calendar userInputDate = DateHelper.extractDateFromString(newDate);
				
				dateAndTime.set(Calendar.YEAR, userInputDate.get(Calendar.YEAR));
				dateAndTime.set(Calendar.DAY_OF_MONTH, userInputDate.get(Calendar.DAY_OF_MONTH));
				dateAndTime.set(Calendar.MONTH, userInputDate.get(Calendar.MONTH));
			} else {
				if(newDate.isEmpty()){
					useUserInputtedDate = false;
					updateDateOrTime();
				}
			}
		}
	}

	@Override
	public void onClockTimeChanged(String newClockTime) {
		Log.d("UserInputClockTime", newClockTime);
	}

	/////////////// OnCaptureToggleButtonChanged: ///////////////
	@Override
	public void onCaptureToggleButtonChanged(boolean buttonState) {
		Log.d("DetailsToggleButton", String.valueOf(buttonState));
		setUpdateSensorAndLocationValues(buttonState);
	}
	
	/////////////// PUBLIC ACCESSORS: ///////////////
	public void setUpdateSensorAndLocationValues(boolean bool){
		updateSensorAndLocationValues = bool;
	}
	
	public void sendDataToDetailsTab(){
		updateDateOrTime();
		
		DetailsTabFragment detailsTab = (DetailsTabFragment) getSupportFragmentManager().findFragmentByTag("detailsTab");
		if(detailsTab != null){
			detailsTab.setLocationDataTextView(latitude, longitude);
			detailsTab.setCollectorTiltAngleDataTextView(String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(collectorTiltAngle)));
			detailsTab.setClockTimeDataTextView(dateAndTime);
		}
	}

}
