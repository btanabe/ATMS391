package com.atms391.android.equations.time;

import static org.junit.Assert.fail;

import org.junit.Test;

public class EMinutesTest {
	private String testName = "EMinutesTest\n";
	private double goldValue = -13.331977686998602;

	@Test
	public void EMInutesTest(){
		double valueFromFunction = EMinutes.getEValueInMinutes(30);
		
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
			
			fail("FAIL:\tEMinutes calcualted its minutes value incorrectly");
		}
	}
}
