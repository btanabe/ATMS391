package com.atms391.android.equations.helpers;

import java.util.Calendar;

public class DateHelper {
	
	public static Calendar extractDateFromString(String dateString){
		Calendar calendar = Calendar.getInstance();
		
		String[] mm_dd_yyyy = dateString.split("-");
		
		calendar.set(Calendar.MONTH, Integer.parseInt(mm_dd_yyyy[0]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(mm_dd_yyyy[1]));
		calendar.set(Calendar.YEAR, Integer.parseInt(mm_dd_yyyy[2]));			

		return calendar;
	}

	public static Calendar extractTimeFromString(String timeString){
		Calendar calendar = Calendar.getInstance();

		String[] hh_mm_ss = timeString.split(":");
		
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh_mm_ss[0]));			
		calendar.set(Calendar.MINUTE, Integer.parseInt(hh_mm_ss[1]));
		calendar.set(Calendar.SECOND, Integer.parseInt(hh_mm_ss[2]));

		return calendar;
	}

	public static boolean isDateStringValid(String dateString){
		String[] mm_dd_yyyy = dateString.split("-");

		// MISSING EITHER A MONTH, DAY, AND/OR YEAR
		if(mm_dd_yyyy.length != 3){
			return false;
		}
		
		// NaN:
		int month = -1, day = -1, year = -1;
		try {
			month = Integer.parseInt(mm_dd_yyyy[0]);
			day = Integer.parseInt(mm_dd_yyyy[1]);
			year = Integer.parseInt(mm_dd_yyyy[2]);
		} catch (Exception e){
			return false;
		}
		
		// ILLEGAL MONTH VALUE:
		if(month > 12 || month < 0){
			return false;
		}
		
		// ILLEGAL DAY VALUE:
		if(day > 31 || day < 0){
			return false;
		}
		
		// ILLEGAL YEAR VALUE:
		if(year < 0){
			return false;
		}
		
		// EVERYTHING SEEMS FINE!
		return true;
	}
}
