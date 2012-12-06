package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class ReflectedInsolationOnCollectorTest {
	private static String testName = "ReflectedInsolationOnCollectorTest";
	private static double goldValue = 8.038475772933678;
	
	@Test
	public void reflectedInsolatoinOnCollectorDegreeTest(){
		double valueFromFunction = ReflectedInsolationOnCollector_Irc.getReflectedInsolationOnCollectorDegrees(1000, 30, 0.10, 30);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" DEGREE:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tReflected insolation on collector (degree) was not calculated correctly");
		}
	}
	
	@Test
	public void reflectedInsolatoinOnCollectorRadiansTest(){
		double valueFromFunction = ReflectedInsolationOnCollector_Irc.getReflectedInsolationOnCollectorRadians(1000, Math.PI / ((double) 6), 0.10, Math.PI / ((double) 6));
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" Radians:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tReflected insolation on collector (radians) was not calculated correctly");
		}
	}
	
	@Test
	public void testReflectedInsolationOnCollectorPreferredMethod_NineAM_North(){
		String testName = "Reflected insolation on collector (Irc)\nClockTime: 09:30.30\nCollector points: 0*\n";
		double goldValue = 7.252583005503576;

		int dayNumber = 339;				// December 4th
		double latitude = 40.112981;		// Champaign, IL
		double longitude = -88.261227;		// Champaign, IL
		double collectorTiltAngle = 39;		// Collector tilt angle

		// Clock time: 09:30.30
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 9);
		currentClockTime.set(Calendar.MINUTE, 30);
		currentClockTime.set(Calendar.SECOND, 30);
		
		double valueFromFunction = ReflectedInsolationOnCollector_Irc.getReflectedInsolationOnCollector(dayNumber, collectorTiltAngle, latitude, longitude, currentClockTime);
		if(valueFromFunction != goldValue){
			StringBuilder failMessage = new StringBuilder();
			failMessage.append(testName);
			failMessage.append("EXPECTED:\t");
			failMessage.append(goldValue);
			failMessage.append(" [W/M^2]\n");
			failMessage.append("GOT:\t\t");
			failMessage.append(valueFromFunction);
			failMessage.append(" [W/M^2]\n");
			
			System.out.println(failMessage.toString());
			
			fail("FAIL:\tReflectedInsolationOnCollector (Irc) was not calculated correctly");
		}
	}
}
