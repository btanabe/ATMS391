package com.atms391.android.gui.tabs;

import com.atms391.android.R;
import com.atms391.android.equations.helpers.NumberPrinterHelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EnergyTabFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}
		
		return (RelativeLayout) inflater.inflate(R.layout.energy_tab_layout, container, false);
	}

	public void setTotalEnergyProducedInOneMonthDataTextView(double kiloWattHours){
		String kiloWattHoursProducedInOneMonth = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(kiloWattHours)) + " kWh";

		TextView totalEnergyProducedInOneMonthDataTextView = (TextView) getActivity().findViewById(R.id.totalEnergyProducedInOneMonthDataTextView);
		if(totalEnergyProducedInOneMonthDataTextView != null){
			totalEnergyProducedInOneMonthDataTextView.setText(kiloWattHoursProducedInOneMonth);
		}
	}
	
	public void setEnergyTabHeaderTextView(double hours){

		TextView hoursOfRefridgeratorOperationTextView = (TextView) getActivity().findViewById(R.id.energyTabHeaderTextView);
		if(hoursOfRefridgeratorOperationTextView != null){
			String hoursOfRefridgeratorOperation = String.valueOf(NumberPrinterHelper.roundToTwoDecimalPlaces(hours)) + " hours.";
			hoursOfRefridgeratorOperationTextView.setText("Your solar panel array would produce enough energy to power a 725W referigerator for " + hoursOfRefridgeratorOperation);
		}
	}
	
}
