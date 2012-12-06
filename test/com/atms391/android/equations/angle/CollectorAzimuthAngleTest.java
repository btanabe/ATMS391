package com.atms391.android.equations.angle;

import static org.junit.Assert.fail;

import org.junit.Test;

public class CollectorAzimuthAngleTest {
	
	@Test
	public void testZeroDegreesCompassHeading(){
		String testName = "CollectorAzimuthAngle - 0* Compass";
		double inputAngle = 0.00;
		double goldValue = 180.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testFourtyFiveDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 45* Compass";
		double inputAngle = 45.00;
		double goldValue = 135.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testNinetyDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 90* Compass";
		double inputAngle = 90.00;
		double goldValue = 90.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testOneThirtyFiveDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 135* Compass";
		double inputAngle = 135.00;
		double goldValue = 45.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testOneEightyDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 180* Compass";
		double inputAngle = 180.00;
		double goldValue = 0.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testTwoHundredTwentyFiveDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 225* Compass";
		double inputAngle = 225.00;
		double goldValue = -45.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testTwoHundredSeventyFiveDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 275* Compass";
		double inputAngle = 270.00;
		double goldValue = -90.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
	
	@Test
	public void testThreeHundredFifteenDegreeCompassHeading(){
		String testName = "CollectorAzimuthAngle - 315* Compass";
		double inputAngle = 315.00;
		double goldValue = -135.00;
		
		double valueFromFunction = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(inputAngle);
		
		if(valueFromFunction != goldValue){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append("\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n");
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tCollectorAzimuthAngle did not calculate " + inputAngle + " properly");
		}
	}
}
