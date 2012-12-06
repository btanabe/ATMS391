package com.atms391.android.equations.helpers;

public class RadiansToDegree {
	/**
	 * Converts radians to degrees without using Math.toDegrees(...)
	 * @param radians	input angle in radians
	 * @return			input angle converted to degrees
	 */
	public static double toDegrees(double radians){
		return (radians *((double) 180)) / ((double) Math.PI);
	}
}
