package com.atms391.android.equations.angle;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

public class CollectorAzimuthAngle {

	public static double getCollectorAzimuthAngleInDegrees(double phoneAzimuthAngleInDegrees){
		double phoneAzimuthAngleInRadians = DegreeToRadians.toRadians(phoneAzimuthAngleInDegrees);

		return RadiansToDegree.toDegrees(getCollectorAzimuthAngleInRadians(phoneAzimuthAngleInRadians));
	}

	public static double getCollectorAzimuthAngleInRadians(double phoneAzimuthAngleInRadians){
		if(phoneAzimuthAngleInRadians != Math.PI){
			return (phoneAzimuthAngleInRadians - Math.PI)* -1.00; 
		} else {
			return 0.00;
		}
	}
}
