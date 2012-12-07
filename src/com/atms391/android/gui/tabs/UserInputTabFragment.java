package com.atms391.android.gui.tabs;

import com.atms391.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class UserInputTabFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		
		return (RelativeLayout) inflater.inflate(R.layout.user_input_tab_layout, container, false);
	}
}
