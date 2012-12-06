package com.atms391.android.equations.time;

import java.util.TimeZone;

public class LocalTimeMeridian {
	private static TimeZone timezone = TimeZone.getDefault();
	
	public static int getLocalTimeMeridian(){
		return 90;		// hardcoded to "Central" time
	}
	
	public static double getLocalTimeMeridianForNorthAmerica(double longitude){
		double offsetLongitude = longitude - 45.00;
		int meridianZone = (int) (offsetLongitude / 15);
		switch(meridianZone){
		case 0:
			return 60;
		case 1:
			return 75;
		case 2:
			return 90;
		case 3:
			return 105;
		case 4:
			return 120;
		case 5:
			return 135;
		case 6:
			return 150;
		default:
			return 90;
		}
	}
}
