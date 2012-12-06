package com.atms391.android.equations.angle;

import java.text.DecimalFormat;

import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.RadiansToDegree;

public class SolarDeclination {
	
	/**
	 * The declination of the Sun is the angle between the rays of the Sun and
	 * the plane of the Earth's equator. This method derives it's equation
	 * from a Taylor Series expansion of the degree formula.
	 * @deprecated			Not guaranteed to return the proper value.
	 * @param dayNumber		number of calendar days since January 1st.
	 * 						dayNumber=0 on January 1st; dayNumber=365 on
	 * 						December 31st.
	 * @return				the solar declination for that day, in radians.
	 * @see <a href="http://naturalfrequency.com/Tregenza_Sharples/Daylight_Algorithms/algorithm_1_11.htm">Daylight Algorithms</a>
	 */
	public static double getSolarDeclinationInRadiansTaylorSeries(int daysSinceJanuaryFirst){
		double dayAngle = ((2*Math.PI)*(daysSinceJanuaryFirst - 1)) / 365;
		
		return 0.006918 - 0.399912*Math.cos(dayAngle) + 0.070257*Math.sin(dayAngle)
			   - 0.006758*Math.cos(2*dayAngle) + 0.000907*Math.sin(2*dayAngle)
			   - 0.002697*Math.cos(3*dayAngle) + 0.001480*Math.sin(3*dayAngle);
	}
	
	/**
	 * The declination of the Sun is the angle between the rays of the Sun and
	 * the plane of the Earth's equator. 
	 * @param dayNumber		number of calendar days since January 1st.
	 * 						dayNumber=0 on January 1st; dayNumber=365 on
	 * 						December 31st.
	 * @return				the solar declination for that day, in radians.
	 */
	public static double getSolarDeclinationInRadians(int daysSinceJanuaryFirst){
		double scalarAngleInRadians = DegreeToRadians.toRadians(-23.44);
		double cosineAngleInRadians = DegreeToRadians.toRadians(((double) (((double)360)/((double)365))*((double) daysSinceJanuaryFirst + 10)));
		
		return scalarAngleInRadians * Math.cos(cosineAngleInRadians);
	}
	
	/**
	 * The declination of the Sun is the angle between the rays of the Sun and
	 * the plane of the Earth's equator. 
	 * @param dayNumber		number of calendar days since January 1st.
	 * 						dayNumber=0 on January 1st; dayNumber=365 on
	 * 						December 31st.
	 * @return				the solar declination for that day, in degrees.
	 */
	public static double getSolarDeclinationInDegrees(int daysSinceJanuaryFirst){
		return RadiansToDegree.toDegrees(getSolarDeclinationInRadians(daysSinceJanuaryFirst));
	}
	
	/**
	 * Rounds a double to the nearest hundredth.  
	 * @param perciseValue	the un-rounded double to be rounded. 
	 * @return				a double rounded to the nearest hundreth.
	 */
	public static double roundToTwoDecimalPlaces(double perciseValue){
		DecimalFormat roundedForm = new DecimalFormat("#.##");
		double roundedValue = Double.valueOf(roundedForm.format(perciseValue));
		
		return roundedValue;
	}
}
