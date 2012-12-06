package com.atms391.android.equations.time;

import java.util.Calendar;

import com.atms391.android.equations.helpers.ClockTimeHelper;

public class SolarTime {
	
	/**
	 * @deprecated
	 * @param longitude
	 * @param eInMinutes
	 * @return
	 */
	public static Calendar getCurrentSolarTimeInMinuts(double longitude, double eInMinutes){
		Calendar calendar = Calendar.getInstance();
		
		return getSolarTimeInMinuts(calendar, longitude, eInMinutes);
	}
	
	/**
	 * @deprecated
	 * @param longitude
	 * @param eInMinutes
	 * @return
	 */
	public static Calendar getSolarTimeInMinuts(Calendar time, double longitude, double eInMinutes){
		int localTimeMeridian = LocalTimeMeridian.getLocalTimeMeridian();
		double minutesSinceMidnightClockTime = ClockTimeHelper.getTimeInMinutesPastMidnight(time);
		
		return getSolarTimeInMinutes(minutesSinceMidnightClockTime, localTimeMeridian, longitude, eInMinutes);
	}
	
	/**
	 * @deprecated
	 * @param longitude
	 * @param eInMinutes
	 * @return
	 */
	public static Calendar getSolarTimeInMinutes(double minutesSinceMidnightClockTime, double localTimeMeridian, double longitude, double eInMinutes){
		double solarTimeInMinutes = minutesSinceMidnightClockTime + 4*(localTimeMeridian - longitude) + eInMinutes;
		
		return ClockTimeHelper.getTimeFromMinutesPastMidnight(solarTimeInMinutes);
	}
	
	/**
	 * Prefered method of determining solar time
	 * @param currentClockTime
	 * @param longitude
	 * @param latitude
	 * @param eMinutes
	 * @return
	 */
	public static Calendar getSolarTimeInMinutes(Calendar currentClockTime, double longitude, double latitude, double eMinutes){
		longitude = Math.abs(longitude);
		double minutesSinceClockTimeMidnight = ClockTimeHelper.getTimeInMinutesPastMidnight(currentClockTime);
		double localMeridianLongitude = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		
		double solarTimeInMinutesSinceMidnight = minutesSinceClockTimeMidnight + (4 * (localMeridianLongitude - longitude)) + eMinutes;
		return ClockTimeHelper.getTimeFromMinutesPastMidnight(solarTimeInMinutesSinceMidnight);
	}
}
