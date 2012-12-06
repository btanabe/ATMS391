package com.atms391.android.equations.helpers;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.atms391.android.equations.atmosphere.AirMassRatio;

public class AirMassRatioTest {
	private String testName = "AirMassRatioTest";
	private double goldValue = 2.0000000000000004;
	
	@Test
	public void airMassRatioDegreesTest(){
		double valueFromFunction = AirMassRatio.getAirMassRatioDegrees(30);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" DEGREES:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tAir mass ratio with degrees as input calculated incorrectly");
		}
	}
	
	@Test
	public void airMassRatioRadiansTest(){
		double valueFromFunction = AirMassRatio.getAirMassRatioRadians((30 * Math.PI) / 180);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" RADIANS:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tAir mass ratio with radians as input calculated incorrectly");
		}
	}
}
