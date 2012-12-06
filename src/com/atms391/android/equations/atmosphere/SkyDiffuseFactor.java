package com.atms391.android.equations.atmosphere;

import com.atms391.android.equations.helpers.DegreeToRadians;

public class SkyDiffuseFactor {
	
	public static double getSkyDiffuseFactor(int dayNumber){
		double fudgeFactor = (double) 360 / 365;
		double angle = fudgeFactor * (dayNumber - 100);
		double angleInRadians = DegreeToRadians.toRadians(angle);
		double sinValue = Math.sin(angleInRadians);
		double diffuseFactor = 0.095 + 0.04*sinValue;
		
		return diffuseFactor;
	}
}
