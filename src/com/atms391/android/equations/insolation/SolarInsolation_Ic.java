package com.atms391.android.equations.insolation;

import java.util.Calendar;

public class SolarInsolation_Ic {
	/**
	 * @deprecated
	 * @param beamInsolationOnCollector
	 * @param diffuseInsolationOnCollector
	 * @param reflectedInsolationOnCollector
	 * @return
	 */
	public static double getSolarInsolation(double beamInsolationOnCollector, double diffuseInsolationOnCollector, double reflectedInsolationOnCollector){
		return beamInsolationOnCollector + diffuseInsolationOnCollector + reflectedInsolationOnCollector;
	}
	
	public static double getSolarInsolationOnCollector(int dayNumber, Calendar clockTime, double longitudeInDegrees, double latitudeInDegrees, double collectorCompassHeadingInDegrees, double collectorTiltAngleInDegrees){
		double beamInsolationOnCollector = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollector(dayNumber, clockTime, longitudeInDegrees, latitudeInDegrees, collectorCompassHeadingInDegrees, collectorTiltAngleInDegrees);
		double diffuseInsolationOnCollector = DiffuseInsolation_Idc.getDiffuseInsolationOnCollector_Idc(dayNumber, collectorTiltAngleInDegrees, latitudeInDegrees, longitudeInDegrees, clockTime);
		double reflectedInsolationOnCollector = ReflectedInsolationOnCollector_Irc.getReflectedInsolationOnCollector(dayNumber, collectorTiltAngleInDegrees, latitudeInDegrees, longitudeInDegrees, clockTime);
		
		return beamInsolationOnCollector + diffuseInsolationOnCollector + reflectedInsolationOnCollector;
	}
}
