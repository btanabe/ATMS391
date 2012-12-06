package com.atms391.android.equations.angle;

import java.util.Calendar;

import com.atms391.android.equations.helpers.ClockTimeHelper;
import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.TimeHelper;
import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.equations.time.SolarTime;

public class HourAngle {

	// TODO WRITE A UNIT TEST FOR THIS:
	/**
	 * This is the prefered method of getting the hour angle
	 * @param currentSolarTime
	 *		A calendar object set to the current solar time
	 * @return
	 * 		A double representing the hour angle in degrees
	 */
	public static double getHourAngleInDegrees(Calendar currentSolarTime){
		double minutesBeforeSolarNoon = ClockTimeHelper.MINUTES_PAST_MIDNIGHT_AT_NOON - ClockTimeHelper.getMinutesPastMidnightFromTime(currentSolarTime);
		double hoursBeforeSolarNoon = minutesBeforeSolarNoon / ((double) 60.00);
		
		double hourAngle = ((double) 15.00) * (hoursBeforeSolarNoon);
		return hourAngle;
	}
	
	public static double getHourAngleInDegrees(double hoursBeforeSolarNoon){
		return ((double) 15) * hoursBeforeSolarNoon;
	}
	
	public static double getHourAngleInRadians(double hoursBeforeSolarNoon){
		return DegreeToRadians.toRadians(((double) 15) * hoursBeforeSolarNoon);
	}
	
	// TODO WRITE A UNIT TEST FOR THIS
	public static double getHoursBeforeSolarNoonNowToday(double longitude){
		return getHoursBeforeSolarNoonNow(longitude, TimeHelper.getTodaysDayNumber());
	}
	
	// TODO WRITE A UNIT TEST FOR THIS
	public static double getHoursBeforeSolarNoonNow(double longitude, int dayNumber){
		Calendar currentSolarTime = SolarTime.getCurrentSolarTimeInMinuts(longitude, EMinutes.getEValueInMinutes(dayNumber));
		
		double currentSolarTimeInMinutesPastMidnight = ClockTimeHelper.getTimeInMinutesPastMidnight(currentSolarTime);
		double minutesBeforeSolarNoon = ClockTimeHelper.MINUTES_PAST_MIDNIGHT_AT_NOON - currentSolarTimeInMinutesPastMidnight;
		
		double hoursBeforeSolarNoon = minutesBeforeSolarNoon / 60.0;
		
		return hoursBeforeSolarNoon;
	}
}
