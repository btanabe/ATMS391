package com.atms391.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.DateTimeKeyListener;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.atms391.android.equations.EquationEngine;
import com.atms391.android.equations.helpers.DateHelper;
import com.atms391.android.equations.helpers.LocationHelper;
import com.atms391.android.equations.helpers.NumberPrinterHelper;
import com.atms391.android.equations.helpers.TimeHelper;
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
	private boolean useUserInputtedDate = false;
	private boolean useUserInputtedClockTime = false;
	private EquationEngine equationEngine = new EquationEngine();

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
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
			equationEngine.updateDateSaveTime();
		}
		
		if(!useUserInputtedClockTime){
			equationEngine.updateTimeSaveDate();
		}
	}

	/////////////// LOCATION LISTNER: ///////////////
	public LocationListener locationListener = new LocationListener(){

		@Override
		public void onLocationChanged(Location location) {
			if(updateSensorAndLocationValues){
				equationEngine.updateLocation(location.getLatitude(), location.getLongitude());
				
				Log.d("MainActivity", "Lat: " + String.valueOf(equationEngine.getLatitudeInDegrees()) + "\t\tLong: " + String.valueOf(equationEngine.getLongitudeInDegrees()));
				updateFragments();
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
				equationEngine.updateCollectorAngles(tiltAngle, azimuthAngle);
				
				updateFragments();
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
		
		if(updateSensorAndLocationValues){
			try {
				double panelArea = Double.valueOf(newPanelAreaString);
				equationEngine.updatePanelArea(panelArea);
			} catch(Exception ex){
				equationEngine.updatePanelArea(1);
			}
		}
	}

	@Override
	public void onPanelEfficiencyChanged(String newPanelEfficiencyString) {
		Log.d("UserInputPanelEfficiency", newPanelEfficiencyString);
		
		if(updateSensorAndLocationValues){
			try {
				double panelEfficiency = Double.valueOf(newPanelEfficiencyString);
				equationEngine.updatePanelEfficiency(panelEfficiency / 100.00);
			} catch(Exception ex){
				equationEngine.updatePanelArea(100.00/100.00);
			}
		}
	}

	// TODO TEST THIS LOGIC
	@Override
	public void onDateChanged(String newDate) {
		Log.d("UserInputDate", newDate);
		
		if(updateSensorAndLocationValues){
			if(DateHelper.isDateStringValid(newDate)){
				useUserInputtedDate = true;
				Calendar userInputDate = DateHelper.extractDateFromString(newDate);
				equationEngine.setDateSaveTime(userInputDate);
			} else {
				useUserInputtedDate = false;
				updateDateOrTime();
			}
		}
	}

	@Override
	public void onClockTimeChanged(String newClockTime) {
		Log.d("UserInputClockTime", newClockTime);
		
		if(updateSensorAndLocationValues){
			if(TimeHelper.isTimeStringValid(newClockTime)){
				useUserInputtedClockTime = true;
				Calendar currentTime = DateHelper.extractTimeFromString(newClockTime);
				equationEngine.setTimeSaveDate(currentTime);
			} else {
				useUserInputtedClockTime = false;
				updateDateOrTime();
			}
		}
	}

	@Override
	public void userInputValueChanged() {
		equationEngine.doUpdate();
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
	
	public void updateFragments(){
		updateDateOrTime();
		equationEngine.updateMonthsEnergy();
		
		sendDataToInsolationTab();
		sendDataToEnergyTab();
		sendDataToDetailsTab();
	}
	
	public void sendDataToDetailsTab(){
		DetailsTabFragment detailsTab = (DetailsTabFragment) getSupportFragmentManager().findFragmentByTag("detailsTab");
		if(detailsTab != null){
			detailsTab.setLocationDataTextView(equationEngine.getLatitudeInDegrees(), equationEngine.getLongitudeInDegrees());
			detailsTab.setCollectorTiltAngleDataTextView(equationEngine.getCollectorTiltAngleInDegrees());
			detailsTab.setClockTimeDataTextView(equationEngine.getDateAndClockTime());
			detailsTab.setDayNumberDataTextView(equationEngine.getDateAndClockTime());
			detailsTab.setCollectorAzimuthAngleDataTextView(equationEngine.getCollectorAzimuthAngleInDegrees());
			detailsTab.setSolarAzimuthAngleDataTextView(equationEngine.getSolarAzimuthAngleInDegrees());
			detailsTab.setSolarIncidenceAngleDataTextView(equationEngine.getSolarIncidenceAngleInDegrees());
			detailsTab.setEMinutesDataTextView(equationEngine.geteMinutes());
			detailsTab.setSolarTimeDataTextView(equationEngine.getSolarTime());
			detailsTab.setSolarDeclinationAngleDataTextView(equationEngine.getSolarDeclinationAngleInDegrees());
			detailsTab.setHourAngleDataTextView(equationEngine.getHourAngleInDegrees());
			detailsTab.setAirMassRatioDataTextView(equationEngine.getAirMassRatio());
			detailsTab.setAtmosphericOpticalDepthDataTextView(equationEngine.getAtmosphericOpticalDepth());
			detailsTab.setSkyDiffuseFactor(equationEngine.getSkyDiffuseFactor());
			detailsTab.setApparentExtraterrestrialSolarInsolation(equationEngine.getApparentExtraterrestrialSolarInsolation());
			detailsTab.setBeamInsolationOnCollectorDataTextView(equationEngine.getBeamInsolationOnCollector_Ibc());
			detailsTab.setDiffuseInsolationOnCollectorDataTextView(equationEngine.getDiffuseInsolationOnCollector_Idc());
			detailsTab.setReflectedSolarInsolationOnCollectorDataTextView(equationEngine.getReflectedInsolationOnCollector_Irc());
			detailsTab.setSolarInsolationOnCollector(equationEngine.getTotalSolarInsolationOnCollector_Ic());
			detailsTab.setBeamInsolationAtEarthsSurfaceDataTextView(equationEngine.getBeamInsolationAtEarthsSurface_Ib());
			detailsTab.setSolarAltitudeAnlgeDataTextView(equationEngine.getSolarAltitudeAngleInDegrees());
		}
	}
	
	private void sendDataToInsolationTab(){
		InsolationTabFragment insolationTab = (InsolationTabFragment) getSupportFragmentManager().findFragmentByTag("insolationTab");
		if(insolationTab != null){
			insolationTab.setSolarInsolationOnCollectorTextView(equationEngine.getTotalSolarInsolationOnCollector_Ic());
			insolationTab.setBeamInsolationOnCollectorDataTextView(equationEngine.getBeamInsolationOnCollector_Ibc());
			insolationTab.setDiffuseInsolationOnCollectorTextView(equationEngine.getDiffuseInsolationOnCollector_Idc());
			insolationTab.setReflectedInsolationOnCollectorTextView(equationEngine.getReflectedInsolationOnCollector_Irc());
		}
	}
	
	private void sendDataToEnergyTab(){
		EnergyTabFragment energyTab = (EnergyTabFragment) getSupportFragmentManager().findFragmentByTag("energyTab");
		if(energyTab != null){
			energyTab.setTotalEnergyProducedInOneMonthDataTextView(equationEngine.getMonthsEnergy());
			energyTab.setEnergyTabHeaderTextView(equationEngine.getMonthsEnergy() / .725);
		}
	}
}
