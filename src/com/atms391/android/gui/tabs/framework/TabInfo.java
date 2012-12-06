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
	
	TabInfo(String tag, Class<?> clss, Bundle args){
		this.tag = tag;
		this.clss = clss;
		this.args = args;
	}
}
