package com.atms391.android.equations.angle;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

// TODO ADD CHCKS FOR DOBULE RETURN VALUES
public class SolarAzimuthAngle {

	public static double getSolarAzimuthAngleInDegrees(double solarDeclinationAngleInDegrees, double hourAngleInDegrees, double solarAltitudeAngleInDegrees, double latitudeInDegrees){
		double solarDeclinationAngleInRadians = DegreeToRadians.toRadians(solarDeclinationAngleInDegrees);
		double hourAngleInRadians = DegreeToRadians.toRadians(hourAngleInDegrees);
		double solarAltitudeAngleInRadians = DegreeToRadians.toRadians(solarAltitudeAngleInDegrees);
		double latitudeInRadians = DegreeToRadians.toRadians(latitudeInDegrees);
		
		double solarAzimuthAngleInRadians = getSolarAzimuthAngleInRadians(solarDeclinationAngleInRadians, hourAngleInRadians, solarAltitudeAngleInRadians, latitudeInRadians);
		
		return RadiansToDegree.toDegrees(solarAzimuthAngleInRadians);
	}
	
	public static double getSolarAzimuthAngleInRadians(double solarDeclinationAngleInRadians, double hourAngleInRadians, double solarAltitudeAngleInRadians, double latitudeInRadians){
		double numerator = Math.cos(solarDeclinationAngleInRadians) * Math.sin(hourAngleInRadians);
		double denominator = Math.cos(solarAltitudeAngleInRadians);
		
		double division = numerator / denominator;
		
		// Azimuth angle before logic:
		double solarAzimuthAngleInRadians = Math.asin(division);
		
		// Make sure to return the correct value:
		return doAzimuthAngleLogicCheck(solarAzimuthAngleInRadians, hourAngleInRadians, solarDeclinationAngleInRadians, latitudeInRadians);
	}
	
	private static double doAzimuthAngleLogicCheck(double azimuthAngleInRadians, double hourAngleInRadians, double solarDeclinationAngleInRadians, double latitudeInRadians){
		double lhsOfCheck = Math.cos(hourAngleInRadians);
		double rhsOfCheck = Math.tan(solarDeclinationAngleInRadians) / Math.tan(latitudeInRadians);
		
		double nonComplementPossibility = azimuthAngleInRadians;
		double complementPossibilty = Math.PI - azimuthAngleInRadians;
		
		if(lhsOfCheck >= rhsOfCheck){
			return getAngleWhoseAbsoluteValueIsLessThanNinetyDegrees(nonComplementPossibility, complementPossibilty);
		} else {
			return getAngleWhoseAbsoluteValueIsGreaterThanNinetyDegrees(nonComplementPossibility, complementPossibilty);
		}
	}
	
	private static double getAngleWhoseAbsoluteValueIsLessThanNinetyDegrees(double a, double b){
		double aAbsoluteValue = Math.abs(a);
		double bAbsoluteValue = Math.abs(b);
		
		if(aAbsoluteValue <= (Math.PI / 2)){
			return a;
		} else if(bAbsoluteValue <= (Math.PI / 2)){
			return b;
		} else {
			return a;	// who knows what's coming back
		}
	}
	
	private static double getAngleWhoseAbsoluteValueIsGreaterThanNinetyDegrees(double a, double b){
		double aAbsoluteValue = Math.abs(a);
		double bAbsoluteValue = Math.abs(b);
		
		if(aAbsoluteValue >= (Math.PI / 2)){
			return a;
		} else if(bAbsoluteValue >= (Math.PI / 2)){
			return b;
		} else {
			return a;	// who knows what's coming back
		}
	} 
}
