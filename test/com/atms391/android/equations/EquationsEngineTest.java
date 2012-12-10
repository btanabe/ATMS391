package com.atms391.android.equations;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class EquationsEngineTest {

	@Test
	public void testEquationsEngineInitialization(){
		String testName = "Total solar insolation on collector (Ibc)\nClockTime: 09:30.30\nCollector points: 45*\n";
		double goldValue = 730.383563904838;

		int dayNumber = 339;				// December 4th
		double latitude = 40.112981;		// Champaign, IL
		double longitude = -88.261227;		// Champaign, IL
		double collectorTiltAngle = 39;		// Collector tilt angle
		double collectorAzimuthAngle = 135;	// Due SE

		// Clock time: 09:30.30
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 9);
		currentClockTime.set(Calendar.MINUTE, 30);
		currentClockTime.set(Calendar.SECOND, 30);
		currentClockTime.set(Calendar.MONTH, Calendar.DECEMBER);
		currentClockTime.set(Calendar.DAY_OF_MONTH, 5);
		currentClockTime.set(Calendar.YEAR, 2012);

		EquationEngine engine = new EquationEngine(dayNumber, currentClockTime, longitude, latitude, collectorAzimuthAngle, collectorTiltAngle);
		double valueFromFunction = engine.getTotalSolarInsolationOnCollector_Ic();

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

			fail("FAIL:\tDiffuseInsolationOnCollector (Idc) was not calculated correctly");
		}
	}

	@Test
	public void testEquationsEngineUpdate(){
		String testName = "Total solar insolation on collector (Ibc)\nClockTime: 09:30.30\nCollector points: 45*\n";
		double goldValue = 692.5109986985451;

		int dayNumber = 339;				// December 4th
		double latitude = 40.112981;		// Champaign, IL
		double longitude = -88.261227;		// Champaign, IL
		double collectorTiltAngle = 39;		// Collector tilt angle
		double collectorAzimuthAngle = 135;	// Due SE

		// Clock time: 09:30.30
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 9);
		currentClockTime.set(Calendar.MINUTE, 30);
		currentClockTime.set(Calendar.SECOND, 30);
		currentClockTime.set(Calendar.MONTH, Calendar.DECEMBER);
		currentClockTime.set(Calendar.DAY_OF_MONTH, 5);
		currentClockTime.set(Calendar.YEAR, 2012);

		EquationEngine engine = new EquationEngine(dayNumber, currentClockTime, longitude, latitude, collectorAzimuthAngle, collectorTiltAngle);
		engine.update(dayNumber, currentClockTime, longitude, latitude, collectorAzimuthAngle, collectorTiltAngle - 5);

		double valueFromFunction = engine.getTotalSolarInsolationOnCollector_Ic();

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

			fail("FAIL:\tDiffuseInsolationOnCollector (Idc) was not calculated correctly");
		}
	}
}