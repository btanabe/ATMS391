package com.atms391.android.gui.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atms391.android.R;

public class InsolationTabFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		
		return (ScrollView) inflater.inflate(R.layout.insolation_tab_layout, container, false);
	}
	
	public void setSolarInsolationOnCollectorTextView(double Ic){
		String toltalSolarInsolationString = String.valueOf(Ic);
		
		TextView solarInsolationDataTextView = (TextView) getActivity().findViewById(R.id.solarInsolationOnCollectorDataTextView);
		if(solarInsolationDataTextView != null){
			solarInsolationDataTextView.setText(toltalSolarInsolationString);
		}
	}
	
	public void setBeamInsolationOnCollectorDataTextView(double Ib){
		String beamInsolationAtEarthsSurface = String.valueOf(Ib);
		
		TextView beamInsolationOnCollectorSurfaceDataTextView = (TextView) getActivity().findViewById(R.id.beamInsolationOnCollectorDataTextView);
		if(beamInsolationOnCollectorSurfaceDataTextView != null){
			beamInsolationOnCollectorSurfaceDataTextView.setText(beamInsolationAtEarthsSurface);
		}
	}
	
	public void setDiffuseInsolationOnCollectorTextView(double Idc){
		String diffuseInsolationOnCollector = String.valueOf(Idc);
		
		TextView diffuseInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.diffuseInsolationOnCollectorDataTextView);
		if(diffuseInsolationOnCollectorDataTextView != null){
			diffuseInsolationOnCollectorDataTextView.setText(diffuseInsolationOnCollector);
		}
	}
	
	public void setReflectedInsolationOnCollectorTextView(double Irc){
		String reflectedInsolationOnCollector = String.valueOf(Irc);
		
		TextView reflectedInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.reflectedInsolationOnCollectorDataTextView);
		if(reflectedInsolationOnCollectorDataTextView != null){
			reflectedInsolationOnCollectorDataTextView.setText(reflectedInsolationOnCollector);
		}
	}
}
