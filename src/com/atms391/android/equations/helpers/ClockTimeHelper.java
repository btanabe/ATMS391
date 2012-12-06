package com.atms391.android.equations.helpers;

import java.util.Calendar;

public class ClockTimeHelper {
	public static double MINUTES_PAST_MIDNIGHT_AT_NOON = 720.0;
	
	public static double getCurrentClockTimeInMinutesPastMidnight(){
		Calendar calendar = Calendar.getInstance();
		
		return getTimeInMinutesPastMidnight(calendar);
	}
	
	public static double getTimeInMinutesPastMidnight(Calendar time){
		double minutesSinceMidnight = (time.get(Calendar.HOUR_OF_DAY) * 60);
		minutesSinceMidnight += time.get(Calendar.MINUTE);
		minutesSinceMidnight += ((double)(time.get(Calendar.SECOND)) / 60);
		
		return minutesSinceMidnight;
	}
	
	public static Calendar getTimeFromMinutesPastMidnight(double minutesPastMidnight){
		Calendar time = Calendar.getInstance();
		
		int hourOfTheDay = (int)(minutesPastMidnight / 60);
		int minute = (int)(minutesPastMidnight % 60);
		int seconds = (int) ((minutesPastMidnight - ((int) minutesPastMidnight)) * 60);
		
		time.set(Calendar.HOUR_OF_DAY, hourOfTheDay);
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.SECOND, seconds);
		
		return time;
	}
	
	public static double getMinutesPastMidnightFromTime(Calendar time){
		double minutesPastMidnight = 0.00;
		
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int minutes = time.get(Calendar.MINUTE);
		int seconds = time.get(Calendar.SECOND);
		
		minutesPastMidnight += ((double) hour) *60;
		minutesPastMidnight += minutes;
		minutesPastMidnight += ((double) seconds) / ((double) 60.00);
		
		
		return minutesPastMidnight;
	}
}
