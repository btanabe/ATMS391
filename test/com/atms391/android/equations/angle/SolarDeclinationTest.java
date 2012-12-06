package com.atms391.android.equations.angle;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atms391.android.equations.angle.SolarDeclination;

public class SolarDeclinationTest {
	private String testName = "SolarDeclinationTest";
	private static int INPUT_DAY = 331;
	
	@Test
	public void testGetSolarDeclinationInRadians(){
		double goldValueInRadians = -0.37468482145501586;
		double valueFromFunction = SolarDeclination.getSolarDeclinationInRadians(INPUT_DAY);
		
		if(goldValueInRadians != valueFromFunction){
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
			
			fail("FAIL:\tSolarDeclinationTest::getSolarDeclinationInRadians(...) returns the incorrect value");
		}
	}
	
	@Test
	public void testGetSolarDeclinationInDegrees(){
		double goldValueInDegrees = -21.467858916985204;
		double valueFromFunction = SolarDeclination.getSolarDeclinationInDegrees(INPUT_DAY);
		
		if(goldValueInDegrees != valueFromFunction){
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
			
			fail("FAIL:\tSolarDeclinationTest::getSolarDeclinationInDegrees(...) returns the incorrect value");
		}
	}
	
	@Test
	public void testRoundToTwoDecimalPlaces(){
		double goldValue = 0.33;
		double valueFromFunction = SolarDeclination.roundToTwoDecimalPlaces((double) 1/3);
		
		if(goldValue != valueFromFunction){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(testName);
			stringBuffer.append("\n");
			stringBuffer.append("EXPECTED:\t");
			stringBuffer.append(goldValue);
			stringBuffer.append("\n");
			stringBuffer.append("GOT:\t\t");
			stringBuffer.append(valueFromFunction);
			stringBuffer.append("\n");
			System.out.println(stringBuffer.toString());
			
			fail("FAIL:\tSolarDeclinationTest::roundToTwoDecimalPlaces(...) returns the incorrect value");
		}
	}
}
