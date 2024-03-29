package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class SolarInsolatoinTest {

	@Test
	public void testTotalSolarInsolationOnCollectorPreferredMethod_NineAM_North(){
		String testName = "Total solar insolation on collector (Ibc)\nClockTime: 09:30.30\nCollector points: 0*\n";
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
		
		double valueFromFunction = SolarInsolation_Ic.getSolarInsolationOnCollector(dayNumber, currentClockTime, longitude, latitude, collectorAzimuthAngle, collectorTiltAngle);
		
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
