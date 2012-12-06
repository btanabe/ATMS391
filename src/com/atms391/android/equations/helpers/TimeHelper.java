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
}
