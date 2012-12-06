package com.atms391.android.equations.angle;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

public class SolarIncidenceAngle {

	public static double getSolarIncidenceAngleInDegrees(double solarAltitudeAngleInDegrees, double solarAzimuthAngleInDegrees, double collectorAzimuthAngleInDegrees, double collectorTiltAngleInDegrees){
		double solarAltitudeAngleInRadians = DegreeToRadians.toRadians(solarAltitudeAngleInDegrees);
		double solarAzimuthAngleInRadians = DegreeToRadians.toRadians(solarAzimuthAngleInDegrees);
		double collectorAzimuthAngleInRadians = DegreeToRadians.toRadians(collectorAzimuthAngleInDegrees);
		double collectorTiltAngleInRadians = DegreeToRadians.toRadians(collectorTiltAngleInDegrees);
		
		return RadiansToDegree.toDegrees(getSolarIncidenceAngleInRadians(solarAltitudeAngleInRadians, solarAzimuthAngleInRadians, collectorAzimuthAngleInRadians, collectorTiltAngleInRadians));
	}
	
	public static double getSolarIncidenceAngleInRadians(double solarAltitudeAngleInRadians, double solarAzimuthAngleInRadians, double collectorAzimuthAngleInRadians, double collectorTiltAngleInRadians){
		double cosValue = Math.cos(solarAltitudeAngleInRadians) * Math.cos(solarAzimuthAngleInRadians - collectorAzimuthAngleInRadians) * Math.sin(collectorTiltAngleInRadians) + Math.sin(solarAltitudeAngleInRadians) * Math.cos(collectorTiltAngleInRadians);
		
		return Math.acos(cosValue);
	}
}
