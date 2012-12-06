package com.atms391.android.equations.insolation;

import java.util.Calendar;

import com.atms391.android.equations.angle.CollectorAzimuthAngle;
import com.atms391.android.equations.angle.HourAngle;
import com.atms391.android.equations.angle.SolarAltitudeAngle;
import com.atms391.android.equations.angle.SolarAzimuthAngle;
import com.atms391.android.equations.angle.SolarDeclination;
import com.atms391.android.equations.angle.SolarIncidenceAngle;
import com.atms391.android.equations.atmosphere.AirMassRatio;
import com.atms391.android.equations.atmosphere.AtmosphericOpticalDepth;
import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.equations.time.SolarTime;

public class BeamInsolationOnCollector_Ibc {
	
	public static double getBeamInsolationOnCollectorDegrees(double beamInsolationAtEarthsSurface, double incidenceAngleBetweenSunAndCollectorFaceDegrees){
		double incidenceAngleBetweenSunAndCollectorFaceRadians = DegreeToRadians.toRadians(incidenceAngleBetweenSunAndCollectorFaceDegrees);
		
		return getBeamInsolationOnCollectorRadians(beamInsolationAtEarthsSurface, incidenceAngleBetweenSunAndCollectorFaceRadians);
	}
	
	public static double getBeamInsolationOnCollectorRadians(double beamInsolationAtEarthsSurface, double incidenceAngleBetweenSunAndCollectorFaceRadians){
		double cosineValue = Math.cos(incidenceAngleBetweenSunAndCollectorFaceRadians);
		double insolation = beamInsolationAtEarthsSurface * cosineValue;
		
		return insolation;
	}
	
	/**
	 * This is the best OO way to get Beam Insolation on Collector
	 * @param dayNumber
	 * 		Days since January 1st where January 1st equals 1
	 * @param currentClockTime
	 * 		Calendar object set to the current clock time, see {@link Calendar}
	 * @param longitude
	 * 		longitude, may be positive or negative
	 * @param latitude
	 * 		latitude, may be positive or negative
	 * @param incidenceAngleBetweenSunAndCollectorFace
	 * 		angle between the sun and the collector face; found by taking the 
	 * 		absolute difference between t
	 * @return
	 * 
	 */
	public static double getBeamInsolationOnCollector(int dayNumber, Calendar currentClockTime, double longitude, double latitude, double collectorCompassHeading, double collectorTiltAngle){
		double eMinutes = EMinutes.getEValueInMinutes(dayNumber);
			Calendar solarTime = SolarTime.getSolarTimeInMinutes(currentClockTime, longitude, latitude, eMinutes);
				double hourAngle = HourAngle.getHourAngleInDegrees(solarTime);
		
				double solarDeclination = SolarDeclination.getSolarDeclinationInDegrees(dayNumber - 1);
				
					double solarAltitudeAngle = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitude, solarDeclination, hourAngle);
					
						double airMassRatio = AirMassRatio.getAirMassRatioDegrees(solarAltitudeAngle);
						
						double atmosphericOpticalDepth = AtmosphericOpticalDepth.getAtmosphericOpticalDpeth(dayNumber);
						
						double apparentExtraterrestrialSolarInsolation = ApparentExtraterrestrialSolarInsolation.getApparentExtraterrestrialSolarInsolation(dayNumber);
						
							double beamInsolationOnEarthsSurface = BeamInsolationAtEarthsSurface.getBeamInsolationAtEarthsSurface(atmosphericOpticalDepth, airMassRatio, apparentExtraterrestrialSolarInsolation);
							
								double solarAzimuthAngle = SolarAzimuthAngle.getSolarAzimuthAngleInDegrees(solarDeclination, hourAngle, solarAltitudeAngle, latitude);
								double collectorAzimuthAngle = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(collectorCompassHeading);
								
								double solarIncidenceAngle = SolarIncidenceAngle.getSolarIncidenceAngleInDegrees(solarAltitudeAngle, solarAzimuthAngle, collectorAzimuthAngle, collectorTiltAngle);
									
									double beamInsolationOnCollector = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollectorDegrees(beamInsolationOnEarthsSurface, solarIncidenceAngle);
		
									
//		System.out.println("------------------------------------------------------------");
//		System.out.println("BEAM-INSOLATION-ON-COLLECTOR");
//		System.out.println("eMinutes:\t\t\t" + eMinutes);
//		System.out.println("SolarTime:\t\t\t" + solarTime.get(Calendar.HOUR_OF_DAY) + ":" + solarTime.get(Calendar.MINUTE) + ":" + solarTime.get(Calendar.SECOND));
//		System.out.println("HourAngle:\t\t\t" + hourAngle);
//		System.out.println("SolarDeclination:\t\t" + solarDeclination);
//		System.out.println("SolarAltitudeAngle:\t\t" + solarAltitudeAngle);
//		System.out.println("AirMassRatio:\t\t\t" + airMassRatio);
//		System.out.println("AtmosphericOpticalDepth:\t" + atmosphericOpticalDepth);
//		System.out.println("ApparentExtraterSolarInsolation:" + apparentExtraterrestrialSolarInsolation);
//		System.out.println("BeamInsolationOnEarthsSurface:\t" + beamInsolationOnEarthsSurface);
//		System.out.println("SolarAzimuthAngle:\t\t" + solarAzimuthAngle);
//		System.out.println("CollectorAzimuthAngle:\t\t" + collectorAzimuthAngle);
//		System.out.println("SolarIncidenceAngle:\t\t" + solarIncidenceAngle);
//		System.out.println("BeamInsolationOnCollector:\t" + beamInsolationOnCollector);
//		System.out.println("------------------------------------------------------------");
		
		// Prevents stupid shit:							
		if(beamInsolationOnCollector == Double.NaN){
			return 0;
		} else {
			return beamInsolationOnCollector;
		}
	}
}
