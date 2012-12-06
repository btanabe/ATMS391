package com.atms391.android.equations.helpers;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class ClockTimeHelperTest {
	private static String testName = "ClockTimeHelperTest";
	private static double goldValueMinutesSinceMidnight = 610.50;
	private static Calendar goldValueTime = Calendar.getInstance();

	static {
		goldValueTime.set(Calendar.HOUR_OF_DAY, 10);
		goldValueTime.set(Calendar.MINUTE, 10);
		goldValueTime.set(Calendar.SECOND, 30);
	}

	@Test
	public void clockTimeHelperTest(){
		Calendar time = Calendar.getInstance();
		time.set(Calendar.HOUR_OF_DAY, 10);
		time.set(Calendar.MINUTE, 10);
		time.set(Calendar.SECOND, 30);

		double valueFromFunction = ClockTimeHelper.getTimeInMinutesPastMidnight(time);

		if(valueFromFunction != goldValueMinutesSinceMidnight){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(testName);
			stringBuffer.append("\n");
			stringBuffer.append("EXPECTED:\t");
			stringBuffer.append(goldValueMinutesSinceMidnight);
			stringBuffer.append("\n");
			stringBuffer.append("GOT:\t\t");
			stringBuffer.append(valueFromFunction);
			stringBuffer.append("\n");
			System.out.println(stringBuffer.toString());

			fail("FAIL:\tClockTimeHelper did not calculate minutes since midnight correctly");
		}
	}

	@Test
	public void minutesSinceMidnightConversionTest(){
		Calendar valueFromFunction = ClockTimeHelper.getTimeFromMinutesPastMidnight(610.50);

		if(valueFromFunction.get(Calendar.HOUR_OF_DAY) != goldValueTime.get(Calendar.HOUR_OF_DAY)){
			fail("FAIL:\tClockTimeHelper did not convert minutes from midnight correctly");
		}

		if(valueFromFunction.get(Calendar.MINUTE) != goldValueTime.get(Calendar.MINUTE)){
			fail("FAIL:\tClockTimeHelper did not convert minutes from midnight correctly");
		}

		if(valueFromFunction.get(Calendar.SECOND) != goldValueTime.get(Calendar.SECOND)){
			fail("FAIL:\tClockTimeHelper did not convert minutes from midnight correctly");
		}
	}
}
