package com.atms391.android.equations.helpers;

import java.text.DecimalFormat;

public class NumberPrinterHelper {
	public static double roundToTwoDecimalPlaces(double perciseValue){ 
		DecimalFormat roundedForm = new DecimalFormat("#.##");
		double roundedValue = Double.valueOf(roundedForm.format(perciseValue));

		return roundedValue;
	}
}
