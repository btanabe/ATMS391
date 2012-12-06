package com.atms391.android.equations.atmosphere;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SkyDiffuseFactorTest {
	private static String testName = "SkyDiffuseFactorTest\n";
	private static double goldValue = 0.13454710360929362;
	
	@Test
	public void skyDiffuseFactorTest(){
		double valueFromFunction = SkyDiffuseFactor.getSkyDiffuseFactor(200);
		
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
			
			fail("FAIL:\tThe sky diffuse factor was not calculated properly");
		}
	}
}
