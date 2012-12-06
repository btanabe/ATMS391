package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ApparentExtraterrestrialSolarInsolationTest {
	private static String testName = "ApparentExtraterrestrialSolarInsolationTest\n";
	private int dayNumber = 200;
	private double goldValue = 1087.9152662095773;
	
	@Test
	public void testApparentExtraterrestrialSolarInsolation(){
		double valueFromFunction = ApparentExtraterrestrialSolarInsolation.getApparentExtraterrestrialSolarInsolation(dayNumber);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\t");
		}
	}
}
