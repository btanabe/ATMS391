package com.atms391.android.location;

import android.location.Location;

public class LocationHelper {

	/**
	 * Returns the current latitude in a human-readable string format.
	 * @param latitude		input latitude
	 * @return				string containing the formatted latitude
	 */
	public static String getPrettyPrintedLatitude(double latitude){
		StringBuilder stringBuilder = new StringBuilder();

		if(latitude > 0){									// In the northern hemisphere
			stringBuilder.append(latitude);
			stringBuilder.append("°");
			stringBuilder.append("N");
		} else if(latitude < 0){							// In the southern hemisphere
			stringBuilder.append(Math.abs(latitude));
			stringBuilder.append("°");
			stringBuilder.append("S");
		} else {											// At the equator
			stringBuilder.append(latitude);
			stringBuilder.append("°");
		}

		return stringBuilder.toString();
	}
	
	/**
	 * Returns the current longitude in a human-readable string format.
	 * @param longitude		input longitude
	 * @return				string containing the formatted longitude
	 */
	public static String getPrettyPrintedLongitude(double longitude){
		StringBuilder stringBuilder = new StringBuilder();

		if(longitude > 0){									// In the eastern hemisphere
			stringBuilder.append(longitude);
			stringBuilder.append("°");
			stringBuilder.append("E");
		} else if(longitude < 0){							// In the western hemisphere
			stringBuilder.append(Math.abs(longitude));
			stringBuilder.append("°");
			stringBuilder.append("W");
		} else {											// At the prime meridian
			stringBuilder.append(longitude);
			stringBuilder.append("°");
		}

		return stringBuilder.toString();
	}

	/**
	 * Returns TRUE if the latitude and longitude are legal
	 * @param location		input location
	 * @return				TRUE if location is valid.
	 */
	public static  boolean isLocationValid(Location location){
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		boolean isValid = true;
		if(latitude > 181 || latitude < -181){
			isValid = false;
		}
		if(longitude > 181 || longitude < -181){
			isValid = false;
		}

		return isValid;
	}
}
