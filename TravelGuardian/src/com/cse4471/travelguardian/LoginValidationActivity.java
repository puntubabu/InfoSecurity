package com.cse4471.travelguardian;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.EditText;

public class LoginValidationActivity extends ActionBarActivity {

    private DatabaseHelper dh;
    private EditText userNameEditableField;
    private EditText passwordEditableField;
    private final static String OPT_NAME = "name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login_validation);

        this.userNameEditableField = (EditText) this
                .findViewById(R.id.etUsername);
        this.passwordEditableField = (EditText) this
                .findViewById(R.id.etPassword);
	}

	
	private void validateLogin(){
        String username = this.userNameEditableField.getText().toString();
        String password = this.passwordEditableField.getText().toString();
        this.dh = new DatabaseHelper(this);
        List<String> names = this.dh.selectAll(username, password);
        if (names.size() > 0) { // Login successful
            // Save username as the name of the player
            SharedPreferences settings = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(OPT_NAME, username);
            editor.commit();

            // Bring up the Home screen
            this.startActivity(new Intent(this, StartActivity.class));
            this.finish();
        } else {
            // Try again?
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Login failed")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                }
                            }).show();
        }
	}
	
	public void onClickLogin(View v){
		if (v.getId() == R.id.btnLoginValid){
			validateLogin();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_validation, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_login_validation, container, false);
			return rootView;
		}
	}

}
