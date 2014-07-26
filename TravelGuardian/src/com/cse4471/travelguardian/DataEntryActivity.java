package com.cse4471.travelguardian;

import java.util.Calendar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class DataEntryActivity extends ActionBarActivity implements OnSeekBarChangeListener{
	
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	
	// Ping frequency SeekBar object
	private SeekBar bar;
	
	// Ping frequency text label object
	private TextView textPingFrequency;

	// Date and time picker buttons
	Button btnSelectDate, btnSelectTime;

	// Variables to store user selected date and time
	public int year, month, day, hour, minute;

	// Variables to display/set the date and time
	private int mYear, mMonth, mDay, mHour, mMinute; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_entry);
		
		// Load SeekBar object and listener     
        bar = (SeekBar)findViewById(R.id.seekbarPingFrequency);
        bar.setOnSeekBarChangeListener(this);
        
        // Make text label for ping frequency value
        textPingFrequency = (TextView)findViewById(R.id.textViewProgress);

		// Get date/time picker button references
		btnSelectDate = (Button)findViewById(R.id.buttonSelectDate);
		btnSelectTime = (Button)findViewById(R.id.buttonSelectTime);
		
		// Set DatePickerDialog to current date
		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		final DatePickerDialog dateDialog =
		    new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);		

		// Set ClickListener on btnSelectDate 
		btnSelectDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				dateDialog.show();
			}
		});
		
		// Set TimePickerDialog to current time
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		final TimePickerDialog timeDialog =
			    new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, true);

		// Set ClickListener on btnSelectTime
		btnSelectTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the TimePickerDialog
				timeDialog.show();
			}
		});

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_data_entry,
					container, false);
			return rootView;
		}
	}
	
    /*
     * SeekBar Ping Frequency ---------------------------------------------------------
     */
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress,
    		boolean fromUser) {   	
    	// Change ping frequency text label with current SeekBar value
    	textPingFrequency.setText("GPS Ping Frequency: " + progress + " hour(s)");
    }
	
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    	// Not implemented    	
    }
    
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    	// Not implemented
    }
	
    /*
     * Date and Time Picker -----------------------------------------------------------
     */

	// Register DatePickerDialog listener
	private DatePickerDialog.OnDateSetListener mDateSetListener =
			new DatePickerDialog.OnDateSetListener() {
		// The callback received when the user sets the date in the DatePickerDialog
		public void onDateSet(DatePicker view, int yearSelected,
				int monthOfYear, int dayOfMonth) {
			year = yearSelected;
			month = monthOfYear;
			day = dayOfMonth;
			// Set the selected date in Set Return Date button
			btnSelectDate.setText("Return Date: "+ month + "/" + day + "/" +year);
		}
	};

	// Register TimePickerDialog listener                 
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener() {
		// The callback received when the user sets the time in the TimePickerDialog
		public void onTimeSet(TimePicker view, int hourOfDay, int min) {
			hour = hourOfDay;
			minute = min;			
			// Set the selected time in the Set Return Time button and accommodate for
			// missing leading zeros
			if(minute < 10){
				btnSelectTime.setText("Return Time: " + hour + ":0" + minute);
			}
			else{
				btnSelectTime.setText("Return Time: " + hour + ":" + minute);
			}
		}
	};

	// Method automatically gets called when you call showDialog()  method
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:			
			// Create a new DatePickerDialog with values you want to show 
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);
			// Create a new TimePickerDialog with values you want to show 
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this,
					mTimeSetListener, mHour, mMinute, false);
		}
		return null;
	}

}
