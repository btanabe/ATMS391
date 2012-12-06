package com.atms391.android.equations.helpers;

public class DegreeToRadians {
	/**
	 * Converts degrees to radians without using Math.toRadians(...)
	 * @param degrees	input angle in degrees
	 * @return			input angle converted to radians
	 */
	public static double toRadians(double degrees){
		return ((double) degrees * Math.PI) / ((double) 180);
	}
}
