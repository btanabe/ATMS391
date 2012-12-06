package com.atms391.android.orientation;

import com.atms391.android.R;
import com.atms391.android.R.layout;
import com.atms391.android.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SensorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sensor, menu);
		return true;
	}

}
