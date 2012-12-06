package com.atms391.android.equations.helpers;

import static org.junit.Assert.fail;

import org.junit.Test;

public class DegreeToRadiansTest {

	@Test
	public void testDegreeToRadians180(){
		double goldValue = Math.PI;
		double valueFromFunction = DegreeToRadians.toRadians(180);
		
		if(goldValue != valueFromFunction){
			fail("FAIL:\tDegreeToRadiansTest::toRadians(180) returns the incorrect value");
		}
	}
	
	@Test
	public void testDegreeToRadians22(){
		double goldValue = ((double) (((double) 11) / ((double) 90)) * Math.PI);
		double valueFromFunction = DegreeToRadians.toRadians(22);
		
		if(goldValue != valueFromFunction){
			fail("FAIL:\tDegreeToRadiansTest::toRadians(22) returns the incorrect value");
		}
	}
}
