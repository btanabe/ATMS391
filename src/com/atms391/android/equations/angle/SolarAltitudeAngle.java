package com.atms391.android.equations.angle;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

public class SolarAltitudeAngle {

	public static double getSolarAltitudeAngleInDegrees(double latitudeInDegrees, double solarDeclinationInDegrees, double hourAngleInDegrees){
		double solarDeclinationInRadians = DegreeToRadians.toRadians(solarDeclinationInDegrees);
		double hourAngleInRadians = DegreeToRadians.toRadians(hourAngleInDegrees);
		
		double solarAltitudeAngleInRadians = getSolarAltitudeAngleInRadians(latitudeInDegrees, solarDeclinationInRadians, hourAngleInRadians);
		
		return RadiansToDegree.toDegrees(solarAltitudeAngleInRadians);
	
	}
	
	public static double getSolarAltitudeAngleInRadians(double latitudeInDegrees, double solarDeclinationInRadians, double hourAngleInRadians){
		double latitudeInRadians = DegreeToRadians.toRadians(latitudeInDegrees);
		
		double cosinePart = Math.cos(latitudeInRadians) * Math.cos(solarDeclinationInRadians) * Math.cos(hourAngleInRadians);
		double sinPart = Math.sin(latitudeInRadians) * Math.sin(solarDeclinationInRadians);
		
		double antiderivativePart = cosinePart + sinPart;
		double solarAltitudeAngleInRadians = Math.asin(antiderivativePart);
		
		return solarAltitudeAngleInRadians;
	}
}
