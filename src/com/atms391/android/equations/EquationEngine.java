package com.atms391.android.equations;

import java.util.Calendar;

import com.atms391.android.equations.angle.CollectorAzimuthAngle;
import com.atms391.android.equations.angle.HourAngle;
import com.atms391.android.equations.angle.SolarAltitudeAngle;
import com.atms391.android.equations.angle.SolarAzimuthAngle;
import com.atms391.android.equations.angle.SolarDeclination;
import com.atms391.android.equations.angle.SolarIncidenceAngle;
import com.atms391.android.equations.atmosphere.AirMassRatio;
import com.atms391.android.equations.atmosphere.AtmosphericOpticalDepth;
import com.atms391.android.equations.atmosphere.SkyDiffuseFactor;
import com.atms391.android.equations.helpers.DateHelper;
import com.atms391.android.equations.helpers.DegreeToRadians;
import com.atms391.android.equations.helpers.TimeHelper;
import com.atms391.android.equations.insolation.ApparentExtraterrestrialSolarInsolation;
import com.atms391.android.equations.insolation.BeamInsolationAtEarthsSurface;
import com.atms391.android.equations.insolation.BeamInsolationOnCollector_Ibc;
import com.atms391.android.equations.insolation.DiffuseInsolation_Idc;
import com.atms391.android.equations.time.EMinutes;
import com.atms391.android.equations.time.SolarTime;

public class EquationEngine {
	// NEEDED FOR ALL CALCULATIONS:
	private int dayNumber = Integer.MAX_VALUE;
	private Calendar dateAndClockTime = Calendar.getInstance();
	private double longitudeInDegrees = Double.MAX_VALUE;
	private double latitudeInDegrees = Double.MAX_VALUE;
	private double collectorAzimuthAngleInDegrees = Double.MAX_VALUE;
	private double collectorTiltAngleInDegrees = Double.MAX_VALUE;
	
	// CALCULATED FROM SEED VALUES:
	private double eMinutes;
	private Calendar solarTime;
	private double solarDeclinationAngleInDegrees;
	private double solarAltitudeAngleInDegrees;
	private double hourAngleInDegrees;
	private double solarAzimuthAngleInDegrees;
	private double solarIncidenceAngleInDegrees;
	private double airMassRatio;
	private double beamInsolationAtEarthsSurface_Ib;
	private double apparentExtraterrestrialSolarInsolation;
	private double atmosphericOpticalDepth;
	private double skyDiffuseFactor;
	private double beamInsolationOnCollector_Ibc;
	private double diffuseInsolationOnCollector_Idc;
	private double reflectedInsolationOnCollector_Irc;
	private double totalSolarInsolationOnCollector_Ic;
	
	
	public EquationEngine(){
		dayNumber = TimeHelper.getTodaysDayNumber() + 1;
		dateAndClockTime = Calendar.getInstance();
	}
	
	public EquationEngine(int dayNumber, Calendar dateAndClockTime, double longitudeInDegrees, double latitudeInDegrees, double collectorCompassHeading, double collectorTiltAngle){
		this.dayNumber = dayNumber;
		this.dateAndClockTime = dateAndClockTime;
		this.longitudeInDegrees = longitudeInDegrees;
		this.latitudeInDegrees = latitudeInDegrees;
		this.collectorTiltAngleInDegrees = collectorTiltAngle;
		collectorAzimuthAngleInDegrees = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(collectorCompassHeading);
		
		doUpdateCalculations();
	}
	
	public void update(int dayNumber, Calendar dateAndClockTime, double longitudeInDegrees, double latitudeInDegrees, double collectorCompassHeading, double collectorTiltAngle){
		this.dayNumber = dayNumber;
		this.dateAndClockTime = dateAndClockTime;
		this.longitudeInDegrees = longitudeInDegrees;
		this.latitudeInDegrees = latitudeInDegrees;
		this.collectorTiltAngleInDegrees = collectorTiltAngle;
		collectorAzimuthAngleInDegrees = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(collectorCompassHeading);

		doUpdateCalculations();
	}
	
	public void updateDateSaveTime(){
		int oldHour = dateAndClockTime.get(Calendar.HOUR_OF_DAY);
		int oldMinute = dateAndClockTime.get(Calendar.MINUTE);
		int oldSecond = dateAndClockTime.get(Calendar.SECOND);
		
		dateAndClockTime = Calendar.getInstance();
		
		dateAndClockTime.set(Calendar.HOUR_OF_DAY, oldHour);
		dateAndClockTime.set(Calendar.MINUTE, oldMinute);
		dateAndClockTime.set(Calendar.SECOND, oldSecond);
		
		doUpdateCalculations();
	}
	
	public void updateTimeSaveDate(){
		int month = dateAndClockTime.get(Calendar.MONTH);
		int dayOfMonth = dateAndClockTime.get(Calendar.DAY_OF_MONTH);
		int year = dateAndClockTime.get(Calendar.YEAR);
		
		dateAndClockTime = Calendar.getInstance();
		
		dateAndClockTime.set(Calendar.MONTH, month);
		dateAndClockTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		dateAndClockTime.set(Calendar.YEAR, year);
		
		doUpdateCalculations();
	}
	
	public void setTimeSaveDate(Calendar time){
		int month = dateAndClockTime.get(Calendar.MONTH);
		int dayOfMonth = dateAndClockTime.get(Calendar.DAY_OF_MONTH);
		int year = dateAndClockTime.get(Calendar.YEAR);
		
		dateAndClockTime = time;
		dateAndClockTime.set(Calendar.MONTH, month);
		dateAndClockTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		dateAndClockTime.set(Calendar.YEAR, year);
		
		doUpdateCalculations();
	}
	
	public void setDateSaveTime(Calendar date){
		int oldHour = dateAndClockTime.get(Calendar.HOUR_OF_DAY);
		int oldMinute = dateAndClockTime.get(Calendar.MINUTE);
		int oldSecond = dateAndClockTime.get(Calendar.SECOND);
		
		dateAndClockTime = date;
		
		dateAndClockTime.set(Calendar.HOUR_OF_DAY, oldHour);
		dateAndClockTime.set(Calendar.MINUTE, oldMinute);
		dateAndClockTime.set(Calendar.SECOND, oldSecond);
		
		doUpdateCalculations();
	}
	
	public void updateLocation(double latitudeInDegrees, double longitudeInDegrees){
		this.latitudeInDegrees = latitudeInDegrees;
		this.longitudeInDegrees = longitudeInDegrees;
		
		doUpdateCalculations();
	}
	
	public void updateCollectorAngles(double collectorTiltAngleInDegrees, double collectorCompassHeadingInDegrees){
		this.collectorTiltAngleInDegrees = collectorTiltAngleInDegrees;
		this.collectorAzimuthAngleInDegrees = CollectorAzimuthAngle.getCollectorAzimuthAngleInDegrees(collectorCompassHeadingInDegrees);
		
		doUpdateCalculations();
	}
	
	private boolean canStartUpdateCalculations(){
		if(dayNumber == Integer.MAX_VALUE){
			return false;
		}
		if(dateAndClockTime == null){
			return false;
		}
		if(longitudeInDegrees == Double.MAX_VALUE){
			return false;
		}
		if(latitudeInDegrees == Double.MAX_VALUE){
			return false;
		}
		if(collectorAzimuthAngleInDegrees == Double.MAX_VALUE){
			return false;
		}
		if(collectorTiltAngleInDegrees == Double.MAX_VALUE){
			return false;
		}
		
		return true;
	}
	
	private void doUpdateCalculations(){
		if(!canStartUpdateCalculations()){
			return;
		}
		
		eMinutes = EMinutes.getEValueInMinutes(dayNumber);
		solarTime = SolarTime.getSolarTimeInMinutes(dateAndClockTime, longitudeInDegrees, latitudeInDegrees, eMinutes);
		hourAngleInDegrees = HourAngle.getHourAngleInDegrees(solarTime);
		solarDeclinationAngleInDegrees = SolarDeclination.getSolarDeclinationInDegrees(dayNumber - 1);
		solarAltitudeAngleInDegrees = SolarAltitudeAngle.getSolarAltitudeAngleInDegrees(latitudeInDegrees, solarDeclinationAngleInDegrees, hourAngleInDegrees);
		airMassRatio = AirMassRatio.getAirMassRatioDegrees(solarAltitudeAngleInDegrees);
		atmosphericOpticalDepth = AtmosphericOpticalDepth.getAtmosphericOpticalDpeth(dayNumber);
		apparentExtraterrestrialSolarInsolation = ApparentExtraterrestrialSolarInsolation.getApparentExtraterrestrialSolarInsolation(dayNumber);
		solarAzimuthAngleInDegrees = SolarAzimuthAngle.getSolarAzimuthAngleInDegrees(solarDeclinationAngleInDegrees, hourAngleInDegrees, solarAltitudeAngleInDegrees, latitudeInDegrees);
		solarIncidenceAngleInDegrees = SolarIncidenceAngle.getSolarIncidenceAngleInDegrees(solarAltitudeAngleInDegrees, solarAzimuthAngleInDegrees, collectorAzimuthAngleInDegrees, collectorTiltAngleInDegrees);
		skyDiffuseFactor = SkyDiffuseFactor.getSkyDiffuseFactor(dayNumber);

		beamInsolationAtEarthsSurface_Ib = BeamInsolationAtEarthsSurface.getBeamInsolationAtEarthsSurface(atmosphericOpticalDepth, airMassRatio, apparentExtraterrestrialSolarInsolation);
		if(beamInsolationAtEarthsSurface_Ib < 0){
			beamInsolationAtEarthsSurface_Ib = 0;
		}
		
		beamInsolationOnCollector_Ibc = BeamInsolationOnCollector_Ibc.getBeamInsolationOnCollectorDegrees(beamInsolationAtEarthsSurface_Ib, solarIncidenceAngleInDegrees);
		if(beamInsolationOnCollector_Ibc < 0){
			beamInsolationOnCollector_Ibc = 0;
		}
		
		diffuseInsolationOnCollector_Idc = DiffuseInsolation_Idc.getDiffuseInsolationOnCollectorDegrees(beamInsolationAtEarthsSurface_Ib, skyDiffuseFactor, collectorTiltAngleInDegrees);
		if(diffuseInsolationOnCollector_Idc < 0){
			diffuseInsolationOnCollector_Idc = 0;
		}
		
		reflectedInsolationOnCollector_Irc = (1.00/5.00) * beamInsolationAtEarthsSurface_Ib;
		reflectedInsolationOnCollector_Irc *= (Math.sin(DegreeToRadians.toRadians(solarAltitudeAngleInDegrees)) + skyDiffuseFactor);
		reflectedInsolationOnCollector_Irc *= ((1 - Math.cos(DegreeToRadians.toRadians(collectorTiltAngleInDegrees))) / 2);
		if(reflectedInsolationOnCollector_Irc < 0){
			reflectedInsolationOnCollector_Irc = 0;
		}
		
		totalSolarInsolationOnCollector_Ic = beamInsolationOnCollector_Ibc + diffuseInsolationOnCollector_Idc + reflectedInsolationOnCollector_Irc;
		if(totalSolarInsolationOnCollector_Ic < 0){
			totalSolarInsolationOnCollector_Ic = 0;
		}
	}

	//////////////// PUBLIC GETTERS: ////////////////
	public int getDayNumber() {
		return dayNumber;
	}

	public Calendar getDateAndClockTime() {
		return dateAndClockTime;
	}

	public double getLongitudeInDegrees() {
		return longitudeInDegrees;
	}

	public double getLatitudeInDegrees() {
		return latitudeInDegrees;
	}

	public double getCollectorAzimuthAngleInDegrees() {
		return collectorAzimuthAngleInDegrees;
	}

	public double getCollectorTiltAngleInDegrees() {
		return collectorTiltAngleInDegrees;
	}

	public double geteMinutes() {
		return eMinutes;
	}

	public Calendar getSolarTime() {
		return solarTime;
	}

	public double getSolarDeclinationAngleInDegrees() {
		return solarDeclinationAngleInDegrees;
	}

	public double getSolarAltitudeAngleInDegrees() {
		return solarAltitudeAngleInDegrees;
	}

	public double getHourAngleInDegrees() {
		return hourAngleInDegrees;
	}

	public double getSolarAzimuthAngleInDegrees() {
		return solarAzimuthAngleInDegrees;
	}

	public double getSolarIncidenceAngleInDegrees() {
		return solarIncidenceAngleInDegrees;
	}

	public double getAirMassRatio() {
		return airMassRatio;
	}

	public double getBeamInsolationAtEarthsSurface_Ib() {
		return beamInsolationAtEarthsSurface_Ib;
	}

	public double getApparentExtraterrestrialSolarInsolation() {
		return apparentExtraterrestrialSolarInsolation;
	}

	public double getAtmosphericOpticalDepth() {
		return atmosphericOpticalDepth;
	}

	public double getSkyDiffuseFactor() {
		return skyDiffuseFactor;
	}

	public double getBeamInsolationOnCollector_Ibc() {
		return beamInsolationOnCollector_Ibc;
	}

	public double getDiffuseInsolationOnCollector_Idc() {
		return diffuseInsolationOnCollector_Idc;
	}

	public double getReflectedInsolationOnCollector_Irc() {
		return reflectedInsolationOnCollector_Irc;
	}

	public double getTotalSolarInsolationOnCollector_Ic() {
		return totalSolarInsolationOnCollector_Ic;
	}
}