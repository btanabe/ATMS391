package com.atms391.android.equations.time;

import static org.junit.Assert.fail;

import org.junit.Test;

public class LocalMeridianTimeTest {
	private String testName = "LocalMeridianTimeTest";
	
	@Test
	public void testAtlanticOrEast(){
		double longitude = 55.40;
		int goldValue = 60;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Atlantic or east:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 55.40 (Atlantic) was calculated incorrectly");
		}
	}
	
	@Test
	public void testEastern(){
		double longitude = 65.40;
		int goldValue = 75;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Eastern:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 65.40 (Eastern) was calculated incorrectly");
		}
	}
	
	@Test
	public void testCentral(){
		double longitude = 85.40;
		int goldValue = 90;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Central:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 85.40 (Central) was calculated incorrectly");
		}
	}
	
	@Test
	public void testMountain(){
		double longitude = 100.40;
		int goldValue = 105;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Mountain:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 100.40 (Mountain) was calculated incorrectly");
		}
	}
	
	@Test
	public void testPacific(){
		double longitude = 115.40;
		int goldValue = 120;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Pacific:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 115.40 (Pacific) was calculated incorrectly");
		}
	}
	
	@Test
	public void testAlaska(){
		double longitude = 125.40;
		int goldValue = 135;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Alaska:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 125.40 (Alaska) was calculated incorrectly");
		}
	}
	
	@Test
	public void testHawaii(){
		double longitude = 145.40;
		int goldValue = 150;
		
		double valueFromFunction = LocalTimeMeridian.getLocalTimeMeridianForNorthAmerica(longitude);
		if(goldValue != valueFromFunction){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(testName);
			stringBuilder.append(" - Hawaii:\n");
			stringBuilder.append("EXPECTED:\t");
			stringBuilder.append(goldValue);
			stringBuilder.append("\n");
			stringBuilder.append("GOT:\t\t");
			stringBuilder.append(valueFromFunction);
			stringBuilder.append("\n\n");		
			System.out.println(stringBuilder.toString());
			
			fail("FAIL:\tLocalMericanTime for longitude of 145.40 (Hawaii) was calculated incorrectly");
		}
	}
}
