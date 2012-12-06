package com.atms391.android.equations.atmosphere;

import com.atms391.android.equations.helpers.DegreeToRadians;

public class AirMassRatio {

	public static double getAirMassRatioDegrees(double solarAltitudeAngleInDegrees){
		double solarAltitudeAngleInRadians = DegreeToRadians.toRadians(solarAltitudeAngleInDegrees);
		
		return getAirMassRatioRadians(solarAltitudeAngleInRadians);
	}
	
	public static double getAirMassRatioRadians(double solarAltitudeAngleInRadians){
		return 1 / Math.sin(solarAltitudeAngleInRadians);
	}
}
