package com.atms391.android.equations.angle;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.atms391.android.equations.angle.SolarAltitudeAngle;

public class SolarAltitudeAngleTest {
	private String testName = "SolarAltitudeAngleTest";
	private double latitudeInDegrees = 40;
	private double solarDeclinationAngleInDegrees = 20;
	private double hourAngleInDegrees = 10;
	private double goldValueInDegrees = 68.24180425881056;
	private double goldValueInRadians = 1.1910441718177327;
	

	@Test
	public void getSolarAltitudeAngleInDegreesTest(){
		double valueFromFunction = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitudeInDegrees, solarDeclinationAngleInDegrees, hourAngleInDegrees);
		
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
			
			fail("FAIL:\tSolar altitude angle in degrees calculated incorrectly");
		}
	}
	
	@Test
	public void getSolarAltitudeAngleInRadiansTest(){
		double valueFromFunction = SolarAltitudeAngle.getSolarAltitudeAngleInRadians(latitudeInDegrees, (solarDeclinationAngleInDegrees * Math.PI) / 180, (hourAngleInDegrees * Math.PI) / 180);
		
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
			
			fail("FAIL:\tSolar altitude angle in radians calculated incorrectly");
		}
	}
	
	@Test
	public void solarAltitudeAngleTest_1(){
		String testName = "SolarAltitudeAngleTest for Champaign, IL on 12/4 at 09:30:30\n";
		double goldValue = 20.058190665409995;
		
		double latitude = 40.112981;
		double solarDeclination = -22.44345281504211;
		double hourAngle = 33.570833333333326;
		
		double valueFromFunction = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitude, solarDeclination, hourAngle);
		if(valueFromFunction != goldValue){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(testName);
			stringBuffer.append("EXPECTED:\t");
			stringBuffer.append(goldValue);
			stringBuffer.append("\n");
			stringBuffer.append("GOT:\t\t");
			stringBuffer.append(valueFromFunction);
			stringBuffer.append("\n");
			System.out.println(stringBuffer.toString());
			
			fail("FAIL:\tSolar altitude angle in radians calculated incorrectly");
		}
	}
}
