package com.atms391.android.equations.angle;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

public class SolarAzimuthAngleTest {
	private String testName = "SolarAzimuthAngleTest";
	private double latitudeInDegreesForChampaign = 40.11;
	private double solarDeclinationAngleInDegrees = 40;
	private double hourAngleInDegrees = 20;
	private double solarAltitudeAngleInDegrees = 10;
	private double goldValueInDegrees = 164.57097698955465;
	private double goldValueInRadians = 2.872305401691554;

	@Test
	public void testGetSolarAzimuthAngleInDegrees(){
		Calendar solarTimeAfternoon = Calendar.getInstance();
		solarTimeAfternoon.set(Calendar.HOUR_OF_DAY, 14);
		
		double valueFromFunction = SolarAzimuthAngle.getSolarAzimuthAngleInDegrees(solarDeclinationAngleInDegrees, hourAngleInDegrees, solarAltitudeAngleInDegrees, latitudeInDegreesForChampaign);
		
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
			
			fail("FAIL:\tSolar azimuth angle in degrees calculated incorrectly");
		}
	}
	
	@Test
	public void testGetSolarAzimuthAngleInRadians(){
		double solarDeclinationAngleInRadians = (solarDeclinationAngleInDegrees * Math.PI) / 180;
		double hourAngleInRadians = (hourAngleInDegrees * Math.PI) / 180;
		double solarAltitudeAngleInRadians = (solarAltitudeAngleInDegrees * Math.PI) / 180;
		
		Calendar solarTimeAfternoon = Calendar.getInstance();
		solarTimeAfternoon.set(Calendar.HOUR_OF_DAY, 14);
		
		double valueFromFunction = SolarAzimuthAngle.getSolarAzimuthAngleInRadians(solarDeclinationAngleInRadians, hourAngleInRadians, solarAltitudeAngleInRadians, DegreeToRadians.toRadians(latitudeInDegreesForChampaign));
		
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
			
			fail("FAIL:\tSolar azimuth angle in radians calculated incorrectly");
		}
	}
}
