package com.cse4471.travelguardian;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSessionManager {
	
    // Shared Preferences reference
    SharedPreferences pref;
     
    // Editor reference for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";
     
    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
     
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_CONTACT_EMAIL = "email";
    public static final String KEY_DESTINATION = "destination";
    public static final String KEY_HOST_CONTACT = "hostcontact";
    public static final String KEY_GPS_LOC = "gpsloc";

    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();
    }
    
    //Create login session
    public void createUserLoginSession(String username){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
         
        // Storing name in pref
        editor.putString(KEY_NAME, username);
         
        // commit changes
        editor.commit();
    }
    
    public void storeContactEmail(String email){
    	editor.putString(KEY_CONTACT_EMAIL, email);
    	editor.commit();
    }
    
    public String getContactEmail(){
    	return pref.getString(KEY_CONTACT_EMAIL, "null");
    }

    public void storeDestination(String destination){
    	editor.putString(KEY_DESTINATION, destination);
    	editor.commit();
    }
    
    public String getDestination(){
    	return pref.getString(KEY_DESTINATION, "null");
    }
    
    public void storeHostContact (String hostContact){
    	editor.putString(KEY_CONTACT_EMAIL, hostContact);
    	editor.commit();
    }
    
    public String getHostContact(){
    	return pref.getString(KEY_CONTACT_EMAIL, "null");
    }
    
    public void storeLastKnownGPSLoc (String gpsLoc){
    	editor.putString(KEY_GPS_LOC, gpsLoc);
    	editor.commit();
    }
    
    public String getLastKnownGPS(){
    	return pref.getString(KEY_GPS_LOC, "null");
    }
    
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){
             
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, StartActivity.class);
             
            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
             
            return true;
        }
        return false;
    }
    
    public HashMap<String, String> getUserDetails(){
        
        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();
         
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
         
        // return user
        return user;
    }
    
    public void logoutUser(){
        
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
        
        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);
         
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
    
    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
	
}
