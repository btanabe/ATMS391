package com.atms391.android.equations.angle;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class HourAngleTest {
	private String testName = "HourAngleTest";
	private double hoursBeforeSolarNoonAfternoon = 3.6;
	private double hoursBeforeSolarNoonMorning = -4.3;
	private double goldValueInDegrees = -64.5;
	private double goldValueInRadians = 0.9424777960769379;

	@Test
	public void getHourAngleInDegreesTest(){
		double valueFromFunction = HourAngle.getHourAngleInDegrees(hoursBeforeSolarNoonMorning);
		
		if(valueFromFunction != goldValueInDegrees){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(testName);
			stringBuffer.append("\n");
			stringBuffer.append("EXPECTED:\t");
			stringBuffer.append(goldValueInDegrees);
			stringBuffer.append("\n");
			stringBuffer.append("GOT:\t\t");
			stringBuffer.append(valueFromFunction);
			stringBuffer.append("\n");
			System.out.println(stringBuffer.toString());
			
			fail("FAIL:\tHour angle in degrees calculated incorrectly");
		}
	}
	
	@Test
	public void getHourAngleInRadiansTest(){
		double valueFromFunction = HourAngle.getHourAngleInRadians(hoursBeforeSolarNoonAfternoon);
		
		if(valueFromFunction != goldValueInRadians){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(testName);
			stringBuffer.append("\n");
			stringBuffer.append("EXPECTED:\t");
			stringBuffer.append(goldValueInRadians);
			stringBuffer.append("\n");
			stringBuffer.append("GOT:\t\t");
			stringBuffer.append(valueFromFunction);
			stringBuffer.append("\n");
			System.out.println(stringBuffer.toString());
			
			fail("FAIL:\tHour angle in radians calculated incorrectly");
		}
	}
	
	@Test
	public void getHourAngleInDegreesBeforeSolarNoonPreferedMethodTest(){
		// Save test name for error message:
		String testName = "Hour Angle - Before Solar Noon:\n";
		
		// Gold value:
		double goldValue = 35.400000000000006;
		
		// Set solar time to 09:38:24 AM
		Calendar solarTime = Calendar.getInstance();
		solarTime.set(Calendar.HOUR_OF_DAY, 9);
		solarTime.set(Calendar.MINUTE, 38);
		solarTime.set(Calendar.SECOND, 24);
		
		double hourAngleInDegrees = HourAngle.getHourAngleInDegrees(solarTime);
		
		if(hourAngleInDegrees != goldValue){
			StringBuilder failMessage = new StringBuilder();
			failMessage.append(testName);
			failMessage.append("EXPECTED:\t");
			failMessage.append(goldValue);
			failMessage.append("\n");
			failMessage.append("GOT:\t\t");
			failMessage.append(hourAngleInDegrees);
			failMessage.append("\n");
			System.out.println(failMessage.toString());
			
			fail("FAIL:\tFailed to calculated the hour angle before solar noon correctly");
		}
	}
	
	@Test
	public void getHourAngleInDegreesAfterSolarNoonPreferedMethodTest(){
		// Save test name for error message:
		String testName = "Hour Angle - After Solar Noon:\n";
		
		// Gold value:
		double goldValue = -37.625;
		
		// Set solar time to 09:38:24 AM
		Calendar solarTime = Calendar.getInstance();
		solarTime.set(Calendar.HOUR_OF_DAY, 14);
		solarTime.set(Calendar.MINUTE, 30);
		solarTime.set(Calendar.SECOND, 30);
		
		double hourAngleInDegrees = HourAngle.getHourAngleInDegrees(solarTime);
		
		if(hourAngleInDegrees != goldValue){
			StringBuilder failMessage = new StringBuilder();
			failMessage.append(testName);
			failMessage.append("EXPECTED:\t");
			failMessage.append(goldValue);
			failMessage.append("\n");
			failMessage.append("GOT:\t\t");
			failMessage.append(hourAngleInDegrees);
			failMessage.append("\n");
			System.out.println(failMessage.toString());
			
			fail("FAIL:\tFailed to calculated the hour angle after solar noon correctly");
		}
	}
}
