package com.atms391.android.gui.tabs.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.atms391.android.R;
import com.atms391.android.gui.tabs.DetailsTabFragment;
import com.atms391.android.gui.tabs.EnergyTabFragment;
import com.atms391.android.gui.tabs.InsolationTabFragment;
import com.atms391.android.gui.tabs.UserInputTabFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class TabsViewPagerFragmentActivity extends FragmentActivity implements OnTabChangeListener, OnPageChangeListener {
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private PagerAdapter mPagerAdapter;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_viewpager_layout);

		this.initialiseTabHost(savedInstanceState);
		if(savedInstanceState != null){
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
		
		this.initializeViewPager();
	}
	
	protected void onSavedInsanceState(Bundle outState){
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onPageScrollStateChanged(int state) {}	// INTENTIONALLY LEFT BLANK

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {} // INTENTIONALLY LEFT BLANK

	@Override
	public void onPageSelected(int position) {
		mTabHost.setCurrentTab(position);
	}

	@Override
	public void onTabChanged(String tabId) {
		int position = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(position);
	}

	private void initializeViewPager(){
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, UserInputTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, InsolationTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, EnergyTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, DetailsTabFragment.class.getName()));
		mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		
		mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(this);

	}
	
	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec("inputTab").setIndicator("Input"), ( tabInfo = new TabInfo("Tab1", UserInputTabFragment.class, args)));
		mapTabInfo.put(tabInfo.getTag(), tabInfo);
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec("insolationTab").setIndicator("Insolation"), ( tabInfo = new TabInfo("Tab2", InsolationTabFragment.class, args)));
		mapTabInfo.put(tabInfo.getTag(), tabInfo);
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec("energyTab").setIndicator("Energy"), ( tabInfo = new TabInfo("Tab3", EnergyTabFragment.class, args)));
		mapTabInfo.put(tabInfo.getTag(), tabInfo);
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec("detailsTab").setIndicator("Details"), ( tabInfo = new TabInfo("Tab3", DetailsTabFragment.class, args)));
		mapTabInfo.put(tabInfo.getTag(), tabInfo);

		mTabHost.setOnTabChangedListener(this);

	}
	
	private static void addTab(TabsViewPagerFragmentActivity activity, TabHost tabHost, TabSpec tabSpec, TabInfo tabInfo){
		tabSpec.setContent(new TabFactory(activity));
		tabHost.addTab(tabSpec);
	}

}
