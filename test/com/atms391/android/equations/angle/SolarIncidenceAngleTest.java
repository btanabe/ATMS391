package com.atms391.android.equations.angle;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SolarIncidenceAngleTest {
	private String testName = "SolarIncidenceAngleTest";
	private double goldValueDegrees = 45.08212939291334;
	private double goldValueRadians = 1.9020850829008085;
	
	@Test
	public void getSolarIncidenceAngleInDegreesTest(){
		double valueFromFunction = SolarIncidenceAngle.getSolarIncidenceAngleInDegrees(40, 20, 10, 5);
		
		if(valueFromFunction != goldValueDegrees){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" DEGREES\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValueDegrees);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");			
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tSolar incidence angle in degress was not calcualted corrected");
		}
	}
	
	@Test
	public void getSolarInvidenceAngleInRadiansTest(){
		double valueFromFunction = SolarIncidenceAngle.getSolarIncidenceAngleInRadians(40, 20, 10, 5);
		
		if(valueFromFunction != goldValueRadians){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" RADIANS\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValueRadians);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");			
			
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tSolar incidence angle in degress was not calcualted corrected");
		}
	}
}
