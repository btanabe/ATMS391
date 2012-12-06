package com.atms391.android.equations.insolation;

import com.atms391.android.equations.helpers.DegreeToRadians;


public class ApparentExtraterrestrialSolarInsolation {

	public static double getApparentExtraterrestrialSolarInsolation(int dayNumber){
		double fudgeFactor = (double) 360/365;
		double angle = fudgeFactor * (dayNumber - 275);
		double angleValueInRadians = DegreeToRadians.toRadians(angle);
		double sinValue = Math.sin(angleValueInRadians);
		
		
		return 1160 + 75 * sinValue;
	}
}
