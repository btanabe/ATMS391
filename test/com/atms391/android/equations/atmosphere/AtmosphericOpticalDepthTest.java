package com.atms391.android.equations.atmosphere;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.atms391.android.equations.atmosphere.AtmosphericOpticalDepth;

public class AtmosphericOpticalDepthTest {
	private static String testName = "AtmosphericOpticalDepthTest\n";
	private double goldValue = 0.2086037156581319;
	@Test
	public void testAtmosphericOpticalDepth(){
		double valueFromFunction = AtmosphericOpticalDepth.getAtmosphericOpticalDpeth(200);
		
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
			
			fail("FAIL:\tAtmospheric optical depth calculated incorrectly");
		}
	}
}
