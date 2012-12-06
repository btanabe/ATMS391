package com.atms391.android.equations.time;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import com.atms391.android.equations.helpers.ClockTimeHelper;

public class SolarTimeTest {
	private double minutesSinceMidnight = 610.50;
	private double longitude = 80;
	private double eInMinutes = 10;
	private double goldValueInMinutes = 660.50;

	@Test
	public void getSolarTimeTest(){
		String testName = "SolarTimeTest";
		Calendar valueFromFunction = SolarTime.getSolarTimeInMinuts(ClockTimeHelper.getTimeFromMinutesPastMidnight(minutesSinceMidnight), longitude, eInMinutes);

		int hourOfTheDay = (int)(goldValueInMinutes / 60);
		int minute = (int)(goldValueInMinutes % 60);
		int seconds = (int) ((goldValueInMinutes - ((int) goldValueInMinutes)) * 60);

		int functionHourOfDay = valueFromFunction.get(Calendar.HOUR_OF_DAY);
		int functionMinute = valueFromFunction.get(Calendar.MINUTE);
		int functionSeconds = valueFromFunction.get(Calendar.SECOND);
		
		if((hourOfTheDay != functionHourOfDay) || (minute != functionMinute) || (seconds != functionSeconds)){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED TIME:\t");
			stringBuilder.append(hourOfTheDay);
			stringBuilder.append(":");
			stringBuilder.append(minute);
			stringBuilder.append(".");
			stringBuilder.append(seconds);
			stringBuilder.append("\n");
			stringBuilder.append("GOT TIME:\t");
			stringBuilder.append(functionHourOfDay);
			stringBuilder.append(":");
			stringBuilder.append(functionMinute);
			stringBuilder.append(".");
			stringBuilder.append(functionSeconds);
			stringBuilder.append("\n");
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tSolar time was not calculated correctly");
		}
	}
	
	@Test
	public void getSolarTimeTestLowerParameters(){
		// Save test name for fail message:
		String testName = "Solar Time Test with smarter parameters:\n";
		
		// Gold hour:
		int goldValueHour = 13;
		// Gold minute:
		int goldValueMinute = 52;
		// Gold second:
		int goldValueSecond = 22;
		
		// Set clock time to 13:36.45:
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 13);
		currentClockTime.set(Calendar.MINUTE, 36);
		currentClockTime.set(Calendar.SECOND, 45);
		
		// longitude = 80; Champaign, IL:
		double longitude = -88.261227;
		
		// latitude = 40.112981; Champaign, IL:
		double latitude = 40.112981;
		
		// E[MINS] = 8.67532 minutes on December 3rd:
		int dayNumber = 338;
		double eMinutes = EMinutes.getEValueInMinutes(dayNumber);

		// Calculate solar time hour, minute, second:
		Calendar currentSolarTime = SolarTime.getSolarTimeInMinutes(currentClockTime, longitude, latitude, eMinutes);
		int solarTimeFromFunctionHour = currentSolarTime.get(Calendar.HOUR_OF_DAY);
		int solarTimeFromFunctionMinute = currentSolarTime.get(Calendar.MINUTE);
		int solarTimeFromFunctionSeconds = currentSolarTime.get(Calendar.SECOND);
		
		// Set up error message on fail
		StringBuffer failMessage = new StringBuffer();
		failMessage.append("******************************\n");
		failMessage.append(testName);
		
		boolean testFail = false;
		if(solarTimeFromFunctionHour != goldValueHour){
			testFail = true;
			
			failMessage.append("EXPECTED HOUR:\t\t");
			failMessage.append(goldValueHour);
			failMessage.append("\n");
			failMessage.append("GOT HOUR:\t\t");
			failMessage.append(solarTimeFromFunctionHour);
			failMessage.append("\n\n");
		}
		if(solarTimeFromFunctionMinute != goldValueMinute){
			testFail = true;
			
			failMessage.append("EXPECTED MINUTE:\t");
			failMessage.append(goldValueMinute);
			failMessage.append("\n");
			failMessage.append("GOT MINUTE:\t\t");
			failMessage.append(solarTimeFromFunctionMinute);
			failMessage.append("\n\n");
		}
		if(solarTimeFromFunctionSeconds != goldValueSecond){
			testFail = true;
			
			failMessage.append("EXPECTED SECOND:\t");
			failMessage.append(goldValueSecond);
			failMessage.append("\n");
			failMessage.append("GOT SECOND:\t\t");
			failMessage.append(solarTimeFromFunctionSeconds);
			failMessage.append("\n\n");
		}
		
		if(testFail){
			failMessage.append("******************************");
			System.out.println(failMessage.toString());
			
			fail("FAIL:\tFailed to calculate solar time correctly");
		}
	}
}
