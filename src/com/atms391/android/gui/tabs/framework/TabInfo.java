package com.atms391.android.gui.tabs.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Maintains extrinsic info of a tab's construct
 * @author Zachary Newell
 *
 */
public class TabInfo {
	private String tag;
	private Class<?> clss;
	private Bundle args;
	private Fragment fragment;
	
	public TabInfo(String tag, Class<?> clss, Bundle args){
		this.tag = tag;
		this.clss = clss;
		this.args = args;
	}

	public String getTag() {
		return tag;
	}

	public Class<?> getClss() {
		return clss;
	}

	public Bundle getArgs() {
		return args;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setClss(Class<?> clss) {
		this.clss = clss;
	}

	public void setArgs(Bundle args) {
		this.args = args;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}
}
