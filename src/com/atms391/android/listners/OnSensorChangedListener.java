package com.atms391.android.listners;

public interface OnSensorChangedListener {
	public void onTiltAngleChanged(double newTiltAngle);
	public void onCompassHeadingAngleChanged(double newCompassHeading);
}
