package com.atms391.android.sensor;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;

public class SensorSamplingService extends Service implements SensorEventListener {

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;	// CAN'T BIND
	}

}
