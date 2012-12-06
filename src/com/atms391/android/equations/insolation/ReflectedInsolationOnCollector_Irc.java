package com.atms391.android.equations.insolation;

import java.util.Calendar;

import com.atms391.android.equations.angle.HourAngle;
import com.atms391.android.equations.angle.SolarAltitudeAngle;
import com.atms391.android.equations.angle.SolarDeclination;
import com.atms391.android.equations.atmosphere.AirMassRatio;
import com.atms391.android.equations.atmosphere.AtmosphericOpticalDepth;
import com.atms391.android.equations.atmosphere.SkyDiffuseFactor;
import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.equations.time.SolarTime;

public class ReflectedInsolationOnCollector_Irc {

	/**
	 * @deprecated
	 * @param beamInsolationOnCollector
	 * @param solarAltitudeAngleInDegrees
	 * @param skyDiffuseFactor
	 * @param collectorTiltAngleInDegrees
	 * @return
	 */
	public static double getReflectedInsolationOnCollectorDegrees(double beamInsolationOnCollector, double solarAltitudeAngleInDegrees, double skyDiffuseFactor, double collectorTiltAngleInDegrees){
		double solarAltitudeAngleInRadians = DegreeToRadians.toRadians(solarAltitudeAngleInDegrees);
		double collectorTiltAngleInRadians = DegreeToRadians.toRadians(collectorTiltAngleInDegrees);
		
		return getReflectedInsolationOnCollectorRadians(beamInsolationOnCollector, solarAltitudeAngleInRadians, skyDiffuseFactor, collectorTiltAngleInRadians);
	}
	
	/**
	 * @deprecated
	 * @param beamInsolationOnCollector
	 * @param solarAltitudeAngleInRadians
	 * @param skyDiffuseFactor
	 * @param collectorTiltAngleInRadians
	 * @return
	 */
	public static double getReflectedInsolationOnCollectorRadians(double beamInsolationOnCollector, double solarAltitudeAngleInRadians, double skyDiffuseFactor, double collectorTiltAngleInRadians){
		double cosineValue = Math.cos(collectorTiltAngleInRadians);
		double division = (((double) 1) - cosineValue) / ((double) 2);
		double sinValue = Math.sin(solarAltitudeAngleInRadians);
		double reflectedInsolation = 0.20 * beamInsolationOnCollector * (sinValue + skyDiffuseFactor) * division;
		
		return reflectedInsolation;
	}
	
	/**
	 * Preferred method of calculating reflected insolation
	 * @param dayNumber
	 * 		days since New Year; January 1st = 1...
	 * @param collectorTiltAngleInDegrees
	 * @param latitudeInDegrees
	 * @param longitudeInDegrees
	 * @param clockTime
	 * @return
	 */
	public static double getReflectedInsolationOnCollector(int dayNumber, double collectorTiltAngleInDegrees, double latitudeInDegrees, double longitudeInDegrees, Calendar clockTime){
		double eMinutes = EMinutes.getEValueInMinutes(dayNumber);
		Calendar solarTime = SolarTime.getSolarTimeInMinutes(clockTime, longitudeInDegrees, latitudeInDegrees, eMinutes);
			double hourAngle = HourAngle.getHourAngleInDegrees(solarTime);
	
			double solarDeclination = SolarDeclination.getSolarDeclinationInDegrees(dayNumber - 1);
			
				double solarAltitudeAngle = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitudeInDegrees, solarDeclination, hourAngle);
				
					double airMassRatio = AirMassRatio.getAirMassRatioDegrees(solarAltitudeAngle);
					
					double atmosphericOpticalDepth = AtmosphericOpticalDepth.getAtmosphericOpticalDpeth(dayNumber);
					
					double apparentExtraterrestrialSolarInsolation = ApparentExtraterrestrialSolarInsolation.getApparentExtraterrestrialSolarInsolation(dayNumber);
					
						double beamInsolationOnEarthsSurface = BeamInsolationAtEarthsSurface.getBeamInsolationAtEarthsSurface(atmosphericOpticalDepth, airMassRatio, apparentExtraterrestrialSolarInsolation);
							double skyDiffuseFactor = SkyDiffuseFactor.getSkyDiffuseFactor(dayNumber);
							
								double reflectedInsolationOnCollector = (1.00/5.00) * beamInsolationOnEarthsSurface;
								reflectedInsolationOnCollector *= (Math.sin(DegreeToRadians.toRadians(solarAltitudeAngle)) + skyDiffuseFactor);
								reflectedInsolationOnCollector *= ((1 - Math.cos(DegreeToRadians.toRadians(collectorTiltAngleInDegrees))) / 2);
		
//		System.out.println("------------------------------------------------------------");
//		System.out.println("REFLECTED-INSOLATION-ON-COLLECTOR");
//		System.out.println("eMinutes:\t\t\t" + eMinutes);
//		System.out.println("SolarTime:\t\t\t" + solarTime.get(Calendar.HOUR_OF_DAY) + ":" + solarTime.get(Calendar.MINUTE) + ":" + solarTime.get(Calendar.SECOND));
//		System.out.println("HourAngle:\t\t\t" + hourAngle);
//		System.out.println("SolarDeclination:\t\t" + solarDeclination);
//		System.out.println("SolarAltitudeAngle:\t\t" + solarAltitudeAngle);
//		System.out.println("AirMassRatio:\t\t\t" + airMassRatio);
//		System.out.println("AtmosphericOpticalDepth:\t" + atmosphericOpticalDepth);
//		System.out.println("ApparentExtraterSolarInsolation:" + apparentExtraterrestrialSolarInsolation);
//		System.out.println("BeamInsolationOnEarthsSurface:\t" + beamInsolationOnEarthsSurface);
//		System.out.println("SkyDiffuseFactor:\t\t" + skyDiffuseFactor);
//		System.out.println("ReflectedInsolationOnCollector:\t" + reflectedInsolationOnCollector);
//		System.out.println("------------------------------------------------------------");
		
								return reflectedInsolationOnCollector;
	}
}
