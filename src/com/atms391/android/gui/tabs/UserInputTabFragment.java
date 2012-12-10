package com.atms391.android.gui.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.atms391.android.R;
import com.atms391.android.listners.OnUserInputChangedListener;

public class UserInputTabFragment extends Fragment {
	private OnUserInputChangedListener mPanelAreaChangedCallback;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		registerCommunicationListeners(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			return null;
		}

		RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.user_input_tab_layout, container, false);
		addListeners(view);
		
		return view;
	}

	private void registerCommunicationListeners(Activity activity) {
		try {
			mPanelAreaChangedCallback = (OnUserInputChangedListener) activity;
		} catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement OnPanelAreaChangedListener");
		}		
	}

	private void addListeners(View view) {
		attachPanelAreaListener(view);
		attachPanelEffiencyListener(view);
		attachDateListener(view);
		attachClockTimeListener(view);
	}
	
	private void attachPanelAreaListener(View view){
		EditText panelAreaEditText = (EditText) view.findViewById(R.id.totalPanelAreaEditText);
		panelAreaEditText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String panelAreaEditTextString = s.toString();
				mPanelAreaChangedCallback.onPanelAreaChanged(panelAreaEditTextString);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

		});
	}
	
	private void attachPanelEffiencyListener(View view){
		EditText panelEfficiencyEditText = (EditText) view.findViewById(R.id.panelEfficiecnyEditText);
		panelEfficiencyEditText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String panelEfficiencyEditTextString = s.toString();
				mPanelAreaChangedCallback.onPanelEfficiencyChanged(panelEfficiencyEditTextString);
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		});
	}
	
	private void attachDateListener(View view){
		EditText dateEditText = (EditText) view.findViewById(R.id.dateInputEditText);
		dateEditText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String dateEditTextString = s.toString();
				mPanelAreaChangedCallback.onDateChanged(dateEditTextString);
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
		});
	}
	
	private void attachClockTimeListener(View view){
		EditText clockTimeEditText = (EditText) view.findViewById(R.id.clockTimeEditText);
		clockTimeEditText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				String clockTimeEditTextString = s.toString();
				mPanelAreaChangedCallback.onClockTimeChanged(clockTimeEditTextString);
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
		});
	}
}
