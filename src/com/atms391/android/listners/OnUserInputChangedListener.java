package com.atms391.android.listners;

public interface OnUserInputChangedListener {
	public void onPanelAreaChanged(String newPanelAreaString);
	public void onPanelEfficiencyChanged(String newPanelEfficiencyString);
	public void onDateChanged(String newDate);
	public void onClockTimeChanged(String newClockTime);
	public void userInputValueChanged();
}
