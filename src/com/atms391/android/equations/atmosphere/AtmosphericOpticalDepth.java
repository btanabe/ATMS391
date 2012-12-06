package com.atms391.android.equations.atmosphere;

import com.atms391.android.equations.helpers.DegreeToRadians;

public class AtmosphericOpticalDepth {

	public static double getAtmosphericOpticalDpeth(int dayNumber){
		double fudgeFactor = (double) 360 / 365;
		double angle = fudgeFactor * (dayNumber - 100);
		double angleInRadians = DegreeToRadians.toRadians(angle);
		double sinValue = Math.sin(angleInRadians);
		double opticalDepth = 0.174 + 0.035 * sinValue;
		
		return opticalDepth;
	}
}
