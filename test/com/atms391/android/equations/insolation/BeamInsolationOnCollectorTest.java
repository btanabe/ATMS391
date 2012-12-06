package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.equations.time.SolarTime;

public class BeamInsolationOnCollectorTest {
	public static String testName = "BeamInsolationOnCollectorTest";
	public static double goldValue = 866.0254037844387;

	@Test
	public void testBeamInsolationOnCollectorInDegrees(){
		double valueFromFunction = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollectorDegrees(1000, 30);

		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" DEGREES\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");

			System.out.print(stringBuilder.toString());

			fail("FAIL:\tBeam insolation on collector (degrees) failed to calcualted correctly");
		}
	}

	@Test
	public void testBeamInsolationOnCollectorInRadians(){
		double valueFromFunction = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollectorRadians(1000, Math.PI / ((double) 6));

		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" RADIANS\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			
			System.out.print(stringBuilder.toString());
			
			fail("FAIL:\tBeam insolation on collector (radians) failed to calcualted correctly");
		}
	}
	
	@Test
	public void testBeamInsolationOnCollectorPreferredMethod_NineAM_North(){
		String testName = "Beam insolation on collector (Ibc)\nClockTime: 09:30.30\nCollector points: 0*\n";
		double goldValue = 678.8917075702033;
		
		int dayNumber = 339;				// December 4th
		double latitude = 40.112981;		// Champaign, IL
		double longitude = -88.261227;		// Champaign, IL
		double collectorAzimuthAngle = 135;	// Due SE
		double collectorTiltAngle = 39;		// Collector tilt angle
		
		// Clock time: 09:30.30
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 9);
		currentClockTime.set(Calendar.MINUTE, 30);
		currentClockTime.set(Calendar.SECOND, 30);
		
		double valueFromFunction = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollector(dayNumber, currentClockTime, longitude, latitude, collectorAzimuthAngle, collectorTiltAngle);
		
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
			
			fail("FAIL:\tBeamInsolationOnCollector (Ibc) was not calculated correctly");
		}
		
	}
}
