package com.atms391.android.equations.helpers;

import static org.junit.Assert.fail;

import org.junit.Test;

public class RadiansToDegreeTest {

	@Test
	public void testRadiansToDegrees2Pi(){
		double goldValue = 360;
		double valueFromFunction = RadiansToDegree.toDegrees(2 * Math.PI);
		
		if(goldValue != valueFromFunction){
			fail("FAIL:\tRadiansToDegreesTest::toDegrees(2pi) returns the incorrect value");
		}
	}
	
	@Test
	public void testRadiansToDegrees4PiOver9(){
		double goldValue = 80;
		double valueFromFunction = RadiansToDegree.toDegrees((((double) 4) * Math.PI) / ((double) 9));
		
		if(goldValue != valueFromFunction){
			fail("FAIL:\tRadiansToDegreesTest::toDegrees(2pi) returns the incorrect value");
		}
	}
}
