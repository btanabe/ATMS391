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
			double hoursOfOperation = NumberPrinterHelper.roundToTwoDecimalPlaces(hours);
			double daysOfOperation = NumberPrinterHelper.roundToTwoDecimalPlaces(hours / 24.00);
			
			StringBuilder message = new StringBuilder();
			message.append("Your solar panel array would produce enough energy in one month to power a 725W refrigerator for ");
			message.append(hoursOfOperation);
			message.append(" hours");
			if(daysOfOperation > 1){
				message.append(" or ");
				message.append(daysOfOperation);
				message.append(" days.");
			} else {
				message.append(".");
			}
			
			hoursOfRefridgeratorOperationTextView.setText(message.toString());
		}
	}
	
}
