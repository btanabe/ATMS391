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

public class DiffuseInsolation_Idc {
	/**
	 * @deprecated
	 * @param beamInsolationAtEarthsSurface
	 * @param skyDiffuseFactor
	 * @param collectorTiltAngleInDegrees
	 * @return
	 * 		Diffuse insolation on collector (Idc) in W/m^2
	 */
	public static double getDiffuseInsolationOnCollectorDegrees(double beamInsolationAtEarthsSurface, double skyDiffuseFactor, double collectorTiltAngleInDegrees){
		double collectorTiltAngleInRadians = DegreeToRadians.toRadians(collectorTiltAngleInDegrees);
		
		return getDiffuseInsolationOnCollectorRadians(beamInsolationAtEarthsSurface, skyDiffuseFactor, collectorTiltAngleInRadians);
	}
	
	/**
	 * @deprecated
	 * @param beamInsolationAtEarthsSurface
	 * @param skyDiffuseFactor
	 * @param collectorTiltAngleRadians
	 * @return
	 * 		Diffuse insolation on collector (Idc) in W/m^2
	 */
	public static double getDiffuseInsolationOnCollectorRadians(double beamInsolationAtEarthsSurface, double skyDiffuseFactor, double collectorTiltAngleRadians){
		double cosineValue = Math.cos(collectorTiltAngleRadians);
		double division = ((double) ((double) 1 + cosineValue) / ((double) 2));
		double diffuseInsolation = beamInsolationAtEarthsSurface * skyDiffuseFactor * division;
		
		return diffuseInsolation;
	}
	
	public static double getDiffuseInsolationOnCollector_Idc(int dayNumber, double collectorTiltAngleInDegrees, double latitudeInDegrees, double longitudeInDegrees, Calendar clockTime){
		double eMinutes = EMinutes.getEValueInMinutes(dayNumber);
			Calendar solarTime = SolarTime.getSolarTimeInMinutes(clockTime, longitudeInDegrees, latitudeInDegrees, eMinutes);
				double hourAngleInDegrees = HourAngle.getHourAngleInDegrees(solarTime);
				double solarDeclinationAngleInDegrees = SolarDeclination.getSolarDeclinationInDegrees(dayNumber - 1);
					double solarAltitudeAngleInDegrees = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitudeInDegrees, solarDeclinationAngleInDegrees, hourAngleInDegrees);
						double airMassRatio = AirMassRatio.getAirMassRatioDegrees(solarAltitudeAngleInDegrees);
						double atmosphericOpticalDepth = AtmosphericOpticalDepth.getAtmosphericOpticalDpeth(dayNumber);
						double apparentExtraterrestrialSolarInsolation = ApparentExtraterrestrialSolarInsolation.getApparentExtraterrestrialSolarInsolation(dayNumber);
							double beamInsolationAtEarthsSurface = BeamInsolationAtEarthsSurface.getBeamInsolationAtEarthsSurface(atmosphericOpticalDepth, airMassRatio, apparentExtraterrestrialSolarInsolation);
							double skyDiffuseFactor = SkyDiffuseFactor.getSkyDiffuseFactor(dayNumber);
								
								double diffuseInsolationOnCollector = getDiffuseInsolationOnCollectorDegrees(beamInsolationAtEarthsSurface, skyDiffuseFactor, collectorTiltAngleInDegrees);
		
//		System.out.println("------------------------------------------------------------");
//		System.out.println("DIFFUSE-INSOLATION-ON-COLLECTOR");
//		System.out.println("eMinutes:\t\t\t" + eMinutes);
//		System.out.println("SolarTime:\t\t\t" + solarTime.get(Calendar.HOUR_OF_DAY) + ":" + solarTime.get(Calendar.MINUTE) + ":" + solarTime.get(Calendar.SECOND));
//		System.out.println("HourAngle:\t\t\t" + hourAngleInDegrees);
//		System.out.println("SolarDeclination:\t\t" + solarDeclinationAngleInDegrees);
//		System.out.println("SolarAltitudeAngle:\t\t" + solarAltitudeAngleInDegrees);
//		System.out.println("AirMassRatio:\t\t\t" + airMassRatio);
//		System.out.println("AtmosphericOpticalDepth:\t" + atmosphericOpticalDepth);
//		System.out.println("ApparentExtraterSolarInsolation:" + apparentExtraterrestrialSolarInsolation);
//		System.out.println("BeamInsolationOnEarthsSurface:\t" + beamInsolationAtEarthsSurface);
//		System.out.println("SkyDiffuseFactor:\t\t" + skyDiffuseFactor);
//		System.out.println("DiffuseInsolationOnCollector:\t" + diffuseInsolationOnCollector);
//		System.out.println("------------------------------------------------------------");
								
								return diffuseInsolationOnCollector;
				
	}
}
