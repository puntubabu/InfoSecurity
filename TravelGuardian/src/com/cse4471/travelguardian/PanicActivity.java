package com.cse4471.travelguardian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PanicActivity extends ActionBarActivity {

	public static UserSessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panic);
        session = new UserSessionManager(getApplicationContext());

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		getVariables();
	}

	private void getVariables(){
		TextView tvContactEmail = (TextView) findViewById(R.id.tvContactEmail);
		TextView tvPanicUsername = (TextView) findViewById(R.id.tvPanicUsername);
		TextView tvPanicDestination = (TextView)findViewById(R.id.tvPanicDestinationVal);
		TextView tvPanicHostContact = (TextView)findViewById(R.id.tvPanicHostContactVal);
		TextView tvPanicGPSLoc = (TextView)findViewById(R.id.tvPanicGPSLocVal);
		
		//Set Contact Email
		tvContactEmail.setText(session.getContactEmail()+"\n\n");
		
		//Set Name Inside PanicText2
		tvPanicUsername.setText(session.getUserDetails().get("name").toString());
		
		//Set Destination
		tvPanicDestination.setText(session.getDestination());
		
		//Set Host Contact
		tvPanicHostContact.setText(session.getHostContact());
		
		//Set Last Known GPS Location
		tvPanicGPSLoc.setText(session.getLastKnownGPS());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.panic, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_panic,
					container, false);
			return rootView;
		}
	}

}
