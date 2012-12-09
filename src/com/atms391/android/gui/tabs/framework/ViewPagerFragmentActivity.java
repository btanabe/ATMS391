package com.atms391.android.gui.tabs.framework;

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

public class ViewPagerFragmentActivity extends FragmentActivity {
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.tabs_viewpager_layout);
		this.initialisePaging();
	}

	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, UserInputTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, InsolationTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, EnergyTabFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, DetailsTabFragment.class.getName()));
		
		pagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
		pager.setAdapter(pagerAdapter);
	}
}
