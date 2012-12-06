package com.atms391.android.gui.tabs.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabsViewPagerFragmentActivity extends FragmentActivity implements OnTabChangeListener, OnPageChangeListener {
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private PagerAdapter mPagerAdapter;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.tabhost);

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
		int position = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(position);
	}

	// TODO FINISH THIS
	private void initializeViewPager(){
		List<Fragment> fragments = new Vector<Fragment>();
//		fragments.add(Fragment.instantiate(this, Tab1Fragment.class.getName()));
//		fragments.add(Fragment.instantiate(this, Tab2Fragment.class.getName()));
//		fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
//		this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
//		this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
//		this.mViewPager.setAdapter(this.mPagerAdapter);
//		this.mViewPager.setOnPageChangeListener(this);

	}
	
	// TODO FINISH THIS
	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
//		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab1").setIndicator("Tab 1"), ( tabInfo = new TabInfo("Tab1", Tab1Fragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab2").setIndicator("Tab 2"), ( tabInfo = new TabInfo("Tab2", Tab2Fragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab3").setIndicator("Tab 3"), ( tabInfo = new TabInfo("Tab3", Tab3Fragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		// Default to first tab

		//this.onTabChanged("Tab1");

		mTabHost.setOnTabChangedListener(this);

	}

}
