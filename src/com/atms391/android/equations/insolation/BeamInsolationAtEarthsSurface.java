package com.atms391.android.equations.insolation;

public class BeamInsolationAtEarthsSurface {

	public static double getBeamInsolationAtEarthsSurface(double atmosphericOptical, double airMassRatio, double apparentExtraterrestrialSolarInsolation){
		double exponetComponet = - atmosphericOptical * airMassRatio;
		double postExponet = Math.exp(exponetComponet);
		double product = apparentExtraterrestrialSolarInsolation * postExponet;
		
		return product;
	}
}
