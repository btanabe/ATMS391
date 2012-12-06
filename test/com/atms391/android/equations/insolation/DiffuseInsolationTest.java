package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class DiffuseInsolationTest {
	private String testName = "DiffuseInsolationTest";
	private double goldValue = 93.30127018922194;

	@Test
	public void diffuseInsolationDegreeTest(){
		double valueFromFunction = DiffuseInsolation_Idc.getDiffuseInsolationOnCollectorDegrees(1000, .1, 30);

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

			fail("FAIL:\tDiffuse insolation (degree) not calculated properly");
		}
	}

	@Test
	public void diffuseInsolationRadianTest(){
		double valueFromFunction = DiffuseInsolation_Idc.getDiffuseInsolationOnCollectorRadians(1000, .1, Math.PI / (double) 6);

		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" RADIAN:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");

			System.out.println(stringBuilder.toString());

			fail("FAIL:\tDiffuse insolation (radians) not calculated properly");
		}
	}
	
	@Test
	public void testDiffuseInsolationOnCollectorPreferredMethod_NineAM_North(){
		String testName = "Diffuse insolation on collector (Idc)\nClockTime: 09:30.30\nCollector points: 0*\n";
		double goldValue = 44.239273329131116;

		int dayNumber = 339;				// December 4th
		double latitude = 40.112981;		// Champaign, IL
		double longitude = -88.261227;		// Champaign, IL
		double collectorTiltAngle = 39;		// Collector tilt angle

		// Clock time: 09:30.30
		Calendar currentClockTime = Calendar.getInstance();
		currentClockTime.set(Calendar.HOUR_OF_DAY, 9);
		currentClockTime.set(Calendar.MINUTE, 30);
		currentClockTime.set(Calendar.SECOND, 30);

		double valueFromFunction = DiffuseInsolation_Idc.getDiffuseInsolationOnCollector_Idc(dayNumber, collectorTiltAngle, latitude, longitude, currentClockTime);
		
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
