package com.atms391.android.constants;

import java.util.Calendar;

public class Constants {
	public static String AUTHOR_NAME = "Zachary Newell";
	public static String CLASS_NAME = "ATMS391";
	public static String SENSOR_GUI_ELEMENTS = "com.atms391.android.SENSOR_GUI_ELEMENTS";
	public static double AVERAGE_DAYLIGHT_HOURS_PER_MONTH = 243.5;
	
	
	
	public static int getDayNumberFromDate(Calendar date){
		return date.get(Calendar.DAY_OF_YEAR);
	}
}
