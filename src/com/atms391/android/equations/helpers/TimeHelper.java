package com.atms391.android.equations.helpers;

import java.util.Calendar;

public class TimeHelper {

	public static Calendar getCurrentTimeAsCalendar(){
		return Calendar.getInstance();
	}
	
	/**
	 * January first returns 0
	 * @return
	 * 		int representing today's day number
	 */
	public static int getTodaysDayNumber(){
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1;
	}
	
	public static String getPrettyPrintedClockString(Calendar time){
		StringBuilder timeString = new StringBuilder();
		
		int hour = time.get(Calendar.HOUR_OF_DAY);
		if(hour < 10){
			timeString.append("0");
		}
		timeString.append(hour);
		timeString.append(":");
		
		int minute = time.get(Calendar.MINUTE);
		if(minute < 10){
			timeString.append("0");
		}
		timeString.append(minute);
		timeString.append(":");
		
		int second = time.get(Calendar.SECOND);
		if(second < 10){
			timeString.append("0");
		}
		timeString.append(second);
		
		
		return timeString.toString();
	}
}
