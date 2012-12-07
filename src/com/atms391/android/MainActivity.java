package com.atms391.android;

import java.util.HashMap;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.atms391.android.gui.tabs.DetailsTabFragment;
import com.atms391.android.gui.tabs.EnergyTabFragment;
import com.atms391.android.gui.tabs.InsolationTabFragment;
import com.atms391.android.gui.tabs.UserInputTabFragment;
import com.atms391.android.gui.tabs.framework.TabFactory;
import com.atms391.android.gui.tabs.framework.TabInfo;

public class MainActivity extends FragmentActivity implements OnTabChangeListener, SensorEventListener{
	private TabHost mTabHost;
	private HashMap<String, TabInfo> tabInfoMap = new HashMap<String, TabInfo>(4);
	private TabInfo mLastTab = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_viewpager_layout);
		
		initializeTabHost(savedInstanceState);
		if(savedInstanceState != null){
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
		
		initializeViewPager();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}
	
	// Private helper functions:
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
		
		onTabChanged("inputTab");
		mTabHost.setOnTabChangedListener(this);
	}
	
	private void addTab(MainActivity activity, TabHost tabHost, TabSpec tabSpec, TabInfo tabInfo){
		tabSpec.setContent(new TabFactory(activity));
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
		
	}
	
	// SENSOR LISTNERS:
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	// OnTabChangeListener LISTENER:
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
					ft.add(R.id.realtabcontent, newTab.getFragment(), newTab.getTag());
				} else {
					ft.attach(newTab.getFragment());
				}
			}

			mLastTab = newTab;
			ft.commit();
			this.getSupportFragmentManager().executePendingTransactions();
		}

	}

}
