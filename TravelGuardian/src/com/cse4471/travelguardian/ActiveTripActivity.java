package com.cse4471.travelguardian;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ActiveTripActivity extends ActionBarActivity {
	
	private CountDownTimer countDownTimer;
	public TextView tv;	
    long diff, milliseconds, endTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_active_trip);
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, 2014);
	    cal.set(Calendar.MONTH, 7);
	    cal.set(Calendar.DAY_OF_MONTH, 10);
	    Date date = cal.getTime(); 
	    long dtMili = System.currentTimeMillis();  
	    Date dateNow = new Date(dtMili);  
	    long remain = date.getTime() - dateNow.getTime();  
	    tv = (TextView) this.findViewById(R.id.txtTimer);
        countDownTimer = new CountDownTimerActivity(remain, 1000);
        tv.setText(tv.getText() + String.valueOf(remain/1000));
        countDownTimer.start();		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
		
	public String timeCalculate(long seconds)   
	   {  
		int day = (int)TimeUnit.SECONDS.toDays(seconds);        
		 long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
		 long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
		 long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);  	   
	     return day + " day(s) " + hours + ":" + minute + ":" + second;  
	   }  
	
	 public class CountDownTimerActivity extends CountDownTimer {
	        public CountDownTimerActivity(long remain, long interval) {
	            super(remain, interval);
	        }
	 
	        @Override
	        public void onFinish() {
	            tv.setText("Time's up!");
	            Intent next = new Intent(getApplicationContext(), PanicActivity.class);    
	    	    startActivity(next);
	        }
	 
	        @Override
	        public void onTick(long millisUntilFinished) {
	            tv.setText("" + timeCalculate(millisUntilFinished/1000) + "");
	        }
	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.active_trip, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_active_trip,
					container, false);
			return rootView;
		}
	}
	
	/** Called when the user clicks the Panic button */
	public void panic(View view) {
		Intent next = new Intent(getApplicationContext(), PanicActivity.class);    
	    startActivity(next);
	    finish();
	}
	
	/** Called when the user clicks the End Trip button */
	public void endTrip(View view) {
		Intent next = new Intent(getApplicationContext(), EndTripActivity.class);    
	    startActivity(next);
	    finish();
	}

}
