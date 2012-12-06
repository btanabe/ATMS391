package com.atms391.android.equations.insolation;

import static org.junit.Assert.fail;

import org.junit.Test;

public class BeamInsolationTest {
	private String testName = "BeamInsolationTest\n";
	private double goldValue = 100 * Math.exp(-200);
	
	@Test
	public void beamInsolationTest(){
		double valueFromFunction = BeamInsolationAtEarthsSurface.getBeamInsolationAtEarthsSurface(100, 2, 100);
		
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
			
			fail("FAIL:\tBeam Insolation calculated incorrectly");
		}
	}
}
