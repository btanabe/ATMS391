package com.atms391.android.gui.tabs;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atms391.android.R;
import com.atms391.android.equations.helpers.LocationHelper;
import com.atms391.android.equations.helpers.NumberPrinterHelper;
import com.atms391.android.equations.helpers.TimeHelper;
import com.atms391.android.listners.OnCaptureToggleButtonChangedListener;

public class DetailsTabFragment extends Fragment {
	private OnCaptureToggleButtonChangedListener mCaptureToggleButtonChangedCallback;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		registerCommunicationListeners(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		
		View view = (ScrollView) inflater.inflate(R.layout.details_tab_layout, container, false);
		addListeners(view);
		
		return view;
	}
	
	private void registerCommunicationListeners(Activity activity) {
		try {
			mCaptureToggleButtonChangedCallback = (OnCaptureToggleButtonChangedListener) activity;
		} catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement OnCaptureToggleButtonChangedListener");
		}			
	}
	
	private void addListeners(View view){
		ToggleButton captureButton = (ToggleButton) view.findViewById(R.id.caputreToggleButton);
		captureButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCaptureToggleButtonChangedCallback.onCaptureToggleButtonChanged(isChecked);
			}
		});
		
		captureButton.setChecked(true);
	}
	
	/////////////// PUBLIC SETTERS: ///////////////
	public void setCollectorTiltAngleDataTextView(String message){
		TextView collectorTiltAngleDataTextView = (TextView) getActivity().findViewById(R.id.collectorTiltAngleDataTextView);
		
		if(collectorTiltAngleDataTextView != null){
			collectorTiltAngleDataTextView.setText(String.valueOf(message));
		}
	}
	
	public void setLocationDataTextView(double latitude, double longitude){
		StringBuilder location = new StringBuilder();
		location.append("(");
		location.append(LocationHelper.getPrettyPrintedLatitude(latitude));
		location.append(", ");
		location.append(LocationHelper.getPrettyPrintedLongitude(longitude));
		location.append(")");
		
		TextView locationDataTextView = (TextView) getActivity().findViewById(R.id.locationDataTextView);
		if(locationDataTextView != null){
			locationDataTextView.setText(location.toString());
		}
	}
	
	public void setClockTimeDataTextView(Calendar clockTime){
		String timeString = TimeHelper.getPrettyPrintedClockString(clockTime);
		
		TextView clockTimeDataTextView = (TextView) getActivity().findViewById(R.id.clockTimeDataTextView);
		if(clockTimeDataTextView != null){
			clockTimeDataTextView.setText(timeString);
		}
	}
	
}
