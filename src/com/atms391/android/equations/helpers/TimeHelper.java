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
		try {
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
		} catch(NullPointerException ex){
			return "00:00:00";
		}
	}
	
	public static boolean isTimeStringValid(String timeString){
		String[] hh_mm_ss = timeString.split(":");
		
		// NOT ENOUGH VALUES:
		if(hh_mm_ss.length != 3){
			return false;
		}
		
		// ILLEGAL HOUR VALUE:
		int hour = -1, minute = -1, second = -1;
		try {
			hour = Integer.parseInt(hh_mm_ss[0]);
			minute = Integer.parseInt(hh_mm_ss[1]);
			second = Integer.parseInt(hh_mm_ss[2]);
		} catch(Exception ex){
			return false;
		}
		
		if(hour > 24 || hour < 0){
			return false;
		}
		
		if(minute >  60 || hour < 0){
			return false;
		}
		
		if(second > 60 || second < 0){
			return false;
		}
		
		return true;
	}
}
