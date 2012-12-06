package com.atms391.android.gui;

import java.util.Map;

import android.widget.Switch;
import android.widget.TextView;

public class GuiElements {
	private Switch captureSensorDataSwitch;
	private TextView locationDataTextView;
	private TextView usesGpsTextView;
	private TextView azimuthDataTextView;
	private TextView pitchDataTextView;
	private TextView rollDataTextView;
	private TextView debuggingMessageBoxTextView;
	
	/////////////// CONSTRUCTORS: ///////////////
	public GuiElements(){}
	
	public GuiElements(Switch captureSensorDataSwitch, TextView locationDataTextView, TextView usesGpsTextView, TextView azimuthDataTextView, TextView pitchDataTextView, TextView rollDataTextView, TextView debuggingMessageBoxTextView){
		this.captureSensorDataSwitch = captureSensorDataSwitch;
		this.locationDataTextView = locationDataTextView;
		this.usesGpsTextView = usesGpsTextView;
		this.azimuthDataTextView = azimuthDataTextView;
		this.pitchDataTextView = pitchDataTextView;
		this.rollDataTextView = rollDataTextView;
		this.debuggingMessageBoxTextView = debuggingMessageBoxTextView;
	}
	
	// TODO IF THIS CLASS IS NECESSARY, THIS MAY BE A GOOD WAY TO ENSURE EXTENDABILITY (USE ID AS KEY?)
	public GuiElements(Map<String, Object> guiElementMap){
		
	}

	/////////////// GETTERS: ///////////////
	public Switch getCaptureSensorDataSwitch(){
		return captureSensorDataSwitch;
	}
	
	public TextView getLocationDataTextView(){
		return locationDataTextView;
	}
	
	public TextView getUsesGpsDataTextView(){
		return usesGpsTextView;
	}

	public TextView getAzimuthDataTextView(){
		return azimuthDataTextView;
	}

	public TextView getPitchDataTextView(){
		return pitchDataTextView;
	}

	public TextView getRollDataTextView(){
		return rollDataTextView;
	}

	public TextView getDebuggingMessageBoxTextView(){
		return debuggingMessageBoxTextView;
	}

	/////////////// SETTERS: ///////////////
	public void setCaptureSensorDataSwitch(Switch captureSensorDataSwitch){
		this.captureSensorDataSwitch = captureSensorDataSwitch; 
	}
	
	public void setLocationDataTextView(TextView locationDataTextView){
		this.locationDataTextView = locationDataTextView;
	}
	
	public void setUsesGpsDataTextView(TextView usesGpsTextView){
		this.usesGpsTextView = usesGpsTextView;
	}

	public void setAzimuthDataTextView(TextView azimuthDataTextView){
		this.azimuthDataTextView = azimuthDataTextView;
	}

	public void setPitchDataTextView(TextView pitchDataTextView){
		this.pitchDataTextView = pitchDataTextView;
	}

	public void setRollDataTextView(TextView rollDataTextView){
		this.rollDataTextView = rollDataTextView;
	}

	public void setDebuggingMessageBoxTextView(TextView debuggingMessageBoxTextView){
		this.debuggingMessageBoxTextView = debuggingMessageBoxTextView;
	}
	
}
