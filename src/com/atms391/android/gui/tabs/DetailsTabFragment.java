package com.atms391.android.gui.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.atms391.android.R;

public class DetailsTabFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		
		return (ScrollView) inflater.inflate(R.layout.details_tab_layout, container, false);
	}

}
