package com.atms391.android.gui.tabs.framework;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

/**
 * A simple factory that returns dummy views to the TabHost
 * @author Zachary Newell
 *
 */
public class TabFactory implements TabContentFactory{
	private final Context mContext;
	
	/**
	 * 
	 * @param context
	 * 			The context in which the returned view from 
	 * 			createTabContent(...) is associated with. 
	 * 				
	 */
	public TabFactory(Context context){
		mContext = context;
	}
	
	/**
	 * Creates a new view.
	 * @param tag
	 * 			the tag associated with the view
	 */
	@Override
	public View createTabContent(String tag) {
		View view = new View(mContext);
		view.setMinimumWidth(0);
		view.setMinimumHeight(0);
		
		return view;
	}

}
