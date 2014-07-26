package com.cse4471.travelguardian;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends ActionBarActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;
    private DatabaseHelper dh;
	
	public void onClick(View v){
        if (v.getId() == R.id.btnSignUp) {
            this.createAccount();
            this.finish();
        }
	}
	
	private void createAccount(){
        String username = this.etUsername.getText().toString();
        String password = this.etPassword.getText().toString();
        String confirm = this.etConfirm.getText().toString();
        if ((password.equals(confirm)) && (!username.equals(""))
                && (!password.equals("")) && (!confirm.equals(""))) {
            this.dh = new DatabaseHelper(this);
            this.dh.insert(username, password);
            // this.labResult.setText("Added");
            Toast.makeText(SignUpActivity.this, "Account created",
                    Toast.LENGTH_SHORT).show();
            this.finish();
            // Bring up the Home screen
            // this.startActivity(new Intent(this, HomeScreen.class));
        } else if ((username.equals("")) || (password.equals(""))
                || (confirm.equals(""))) {
            Toast.makeText(SignUpActivity.this, "Missing entry", Toast.LENGTH_SHORT)
                    .show();
        } else if (!password.equals(confirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Passwords do not match")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                }
                             }).show();
        }

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_sign_up);

        this.etUsername = (EditText) this.findViewById(R.id.etUsername);
        this.etPassword = (EditText) this.findViewById(R.id.etPassword);
        this.etConfirm = (EditText) this
                .findViewById(R.id.etConfirmPassword);
        //View btnAdd = this.findViewById(R.id.btnSignUp);
        //btnAdd.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_sign_up,
					container, false);
			return rootView;
		}
	}

}