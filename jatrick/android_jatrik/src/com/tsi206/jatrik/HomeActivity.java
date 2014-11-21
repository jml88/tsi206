package com.tsi206.jatrik;

import com.tsi206.jatrik.util.PrefUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class HomeActivity extends Activity {
	
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		username = PrefUtils.getFromPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		password = PrefUtils.getFromPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		
		//final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

	    //if ( customTitleSupported ) {
	        getWindow().setTitle("Mi equipo - " + username);
	    //}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return logout();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean logout(){
		String loggedInUserName = PrefUtils.getFromPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		String loggedInUserPassword = PrefUtils.getFromPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		
		if (loggedInUserName != null & loggedInUserPassword != null){
			PrefUtils.saveToPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
			PrefUtils.saveToPrefs(HomeActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
			System.out.println("LOGOUT");
			finish();
			return true;
		}
		
		return false;
	}
}
