package com.atms391.android.equations.time;

import com.atms391.android.equations.helpers.DegreeToRadians;


public class EMinutes {
	
	public static double getEValueInMinutes(int dayNumber){
		double B = ((double)(360.0 / 364.0))*((double) dayNumber - 81.00);
		B = DegreeToRadians.toRadians(B);
		
		double E = 9.87 * Math.sin(2*B) - 7.53 * Math.cos(B) - 1.5 * Math.sin(B);
		
		return E;
	}
	
	/**
	 * @deprecated
	 * @param B
	 * @return
	 */
	public static double getEValueInMinutes(double B){
//		double solarAltitudeAngleInRadians = DegreeToRadians.toRadians(B);
		
//		return getEValueInMinutesRadians(solarAltitudeAngleInRadians);
		
		return 0.00;
	}
	
	/**
	 * @deprecated
	 * @param B
	 * @return
	 */
	public static double getEValueInMinutesRadians(double B){
//		return 9.87 * Math.sin(2*B) - 7.53 * Math.cos(B) - 1.5 * Math.sin(B);
		
		return 0.00;
	}
}
