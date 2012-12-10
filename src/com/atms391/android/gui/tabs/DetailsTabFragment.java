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
	public void setLocationDataTextView(double latitude, double longitude){
		TextView locationDataTextView = (TextView) getActivity().findViewById(R.id.locationDataTextView);
		if(locationDataTextView != null){
			StringBuilder location = new StringBuilder();
			location.append("(");
			location.append(LocationHelper.getPrettyPrintedLatitude(latitude));
			location.append(", ");
			location.append(LocationHelper.getPrettyPrintedLongitude(longitude));
			location.append(")");
			locationDataTextView.setText(location.toString());
		}
	}
	
	public void setClockTimeDataTextView(Calendar clockTime){
		TextView clockTimeDataTextView = (TextView) getActivity().findViewById(R.id.clockTimeDataTextView);
		String timeString = TimeHelper.getPrettyPrintedClockString(clockTime);
		if(clockTimeDataTextView != null){
			clockTimeDataTextView.setText(timeString);
		}
	}
	
	public void setDayNumberDataTextView(Calendar dayNumber){
		TextView dayNumberDataTextView = (TextView) getActivity().findViewById(R.id.dayNumberDataTextView);
		if(dayNumberDataTextView != null){
			String dayNumberString = String.valueOf(dayNumber.get(Calendar.DAY_OF_YEAR));
			dayNumberDataTextView.setText(dayNumberString);
		}
	}

	public void setCollectorTiltAngleDataTextView(double collectorTiltAngle){
		TextView collectorTiltAngleDataTextView = (TextView) getActivity().findViewById(R.id.collectorTiltAngleDataTextView);
		if(collectorTiltAngleDataTextView != null){
			String tiltAngle = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(collectorTiltAngle));
			collectorTiltAngleDataTextView.setText(tiltAngle);
		}
	}
	
	public void setCollectorAzimuthAngleDataTextView(double collectorAzimuthAngle){
		TextView collectorAzimuthAngleDataTextView = (TextView) getActivity().findViewById(R.id.collectorAzimuthAngleDataTextView);
		if(collectorAzimuthAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(collectorAzimuthAngle));
			collectorAzimuthAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSolarAzimuthAngleDataTextView(double solarAzimuthAngle){
		TextView solarAzimuthAngleDataTextView = (TextView) getActivity().findViewById(R.id.solarAzimuthAngleDataTextView);
		if(solarAzimuthAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(solarAzimuthAngle));
			solarAzimuthAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSolarIncidenceAngleDataTextView(double solarIncidenceAngle){
		TextView solarIncidenceAngleDataTextView = (TextView) getActivity().findViewById(R.id.solarIncidenceAngleDataTextView);
		if(solarIncidenceAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(solarIncidenceAngle));
			solarIncidenceAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setEMinutesDataTextView(double eMinutes){
		TextView solarIncidenceAngleDataTextView = (TextView) getActivity().findViewById(R.id.eMinutesDataTextView);
		if(solarIncidenceAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(eMinutes));
			solarIncidenceAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSolarTimeDataTextView(Calendar solarTime){
		TextView solarTimeDataTextView = (TextView) getActivity().findViewById(R.id.solarTimeDataTextView);
		if(solarTimeDataTextView != null){
			String timeString = TimeHelper.getPrettyPrintedClockString(solarTime);
			solarTimeDataTextView.setText(timeString);
		}
	}
	
	public void setSolarDeclinationAngleDataTextView(double solarDeclinationAngle){
		TextView solarDeclinationAngleDataTextView = (TextView) getActivity().findViewById(R.id.solarDeclinationAngleDataTextView);
		if(solarDeclinationAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(solarDeclinationAngle));
			solarDeclinationAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setHourAngleDataTextView(double hourAngle){
		TextView hourAngleDataTextView = (TextView) getActivity().findViewById(R.id.hourAngleDataTextView);
		if(hourAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(hourAngle));
			hourAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setAirMassRatioDataTextView(double airMassRatio){
		TextView airMassRatioDataTextView = (TextView) getActivity().findViewById(R.id.airMassRatioDataTextView);
		if(airMassRatioDataTextView != null){
			String collectorAzimuthAngleString = null;
			if(airMassRatio > Double.MAX_VALUE){
				collectorAzimuthAngleString = "INFINITY";
			} else {
				collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(airMassRatio));
			}
			airMassRatioDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setAtmosphericOpticalDepthDataTextView(double atmosphericOpticalDepth){
		TextView atmosphericOpticalDepthDataTextView = (TextView) getActivity().findViewById(R.id.atmosphericOpticalDepthDataTextView);
		if(atmosphericOpticalDepthDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(atmosphericOpticalDepth));
			atmosphericOpticalDepthDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSkyDiffuseFactor(double skyDiffuseFactor){
		TextView skyDiffuseFactorDataTextView = (TextView) getActivity().findViewById(R.id.skyDiffuseFactorDataTextView);
		if(skyDiffuseFactorDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(skyDiffuseFactor));
			skyDiffuseFactorDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setApparentExtraterrestrialSolarInsolation(double apparentExtraterrestrialSolarInsolation){
		TextView apparentExtraterrestrialSolarInsolationDataTextView = (TextView) getActivity().findViewById(R.id.apparentExtraterrestrialSolarInsolationDataTextView);
		if(apparentExtraterrestrialSolarInsolationDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(apparentExtraterrestrialSolarInsolation));
			apparentExtraterrestrialSolarInsolationDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setBeamInsolationOnCollectorDataTextView(double beamInsolationOnCollector){
		TextView beamInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.beamInsolationOnCollectorDataTextView);
		if(beamInsolationOnCollectorDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(beamInsolationOnCollector));
			beamInsolationOnCollectorDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setDiffuseInsolationOnCollectorDataTextView(double diffuseInsolationOnCollector){
		TextView diffuseInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.diffuseInsolationOnCollectorDataTextView);
		if(diffuseInsolationOnCollectorDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(diffuseInsolationOnCollector));
			diffuseInsolationOnCollectorDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setReflectedSolarInsolationOnCollectorDataTextView(double reflectedSolarInsolationOnCollector){
		TextView reflectedInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.reflectedInsolationOnCollectorDataTextView);
		if(reflectedInsolationOnCollectorDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(reflectedSolarInsolationOnCollector));
			reflectedInsolationOnCollectorDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSolarInsolationOnCollector(double solarInsolationOnCollector){
		TextView solarInsolationOnCollectorDataTextView = (TextView) getActivity().findViewById(R.id.solarInsolationOnCollectorDataTextView);
		if(solarInsolationOnCollectorDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(solarInsolationOnCollector));
			solarInsolationOnCollectorDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setBeamInsolationAtEarthsSurfaceDataTextView(double beamInsolationAtEarthsSurface){
		TextView beamInsolationAtEarthsSurfaceDataTextView = (TextView) getActivity().findViewById(R.id.beamInsolationAtEarthsSurfaceDataTextView);
		if(beamInsolationAtEarthsSurfaceDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(beamInsolationAtEarthsSurface));
			beamInsolationAtEarthsSurfaceDataTextView.setText(collectorAzimuthAngleString);
		}
	}
	
	public void setSolarAltitudeAnlgeDataTextView(double solarAltitudeAngle){
		TextView solarAltitudeAngleDataTextView = (TextView) getActivity().findViewById(R.id.solarAltitudeAngleDataTextView);
		if(solarAltitudeAngleDataTextView != null){
			String collectorAzimuthAngleString = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(solarAltitudeAngle));
			solarAltitudeAngleDataTextView.setText(collectorAzimuthAngleString);
		}
	}
}
