package zzzSandbox;

import java.util.Calendar;

import com.atms391.android.constants.Constants;

public class Sandbox {

	public static void main(String[] args){
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, Calendar.JANUARY);
		date.set(Calendar.DAY_OF_MONTH, 5);
		
		System.out.println(Constants.getDayNumberFromDate(date));
	}
}
