package com.tsi206.jatrik;

import java.util.concurrent.ExecutionException;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.tsi206.jatrik.util.PrefUtils;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		Handler h = new Handler();
	    h.post(new Runnable() {     
	        @SuppressLint("NewApi")
			@Override
	        public void run() {
	            getActionBar().hide();
	        }
	    });
		
		setContentView(R.layout.activity_login);
		
		String loggedInUserName = PrefUtils.getFromPrefs(LoginActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		String loggedInUserPassword = PrefUtils.getFromPrefs(LoginActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		
		if (loggedInUserName != null & loggedInUserPassword != null){
			this.login(loggedInUserName, loggedInUserPassword);
			System.out.println("login automático");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void login(String username, String password){
String urlString = "http://192.168.56.1:8080/servicios_jatrik/rest/api/login/" + username + '/' + password;
	    
	    if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
	    	try {
				String result = new CallAPI().execute(urlString).get();
				System.out.println(result);
				
				if(result.equals("LoginFAIL")){
					Toast.makeText(this, "Usuario o contraseña incorrectos",
				    Toast.LENGTH_LONG).show();
				}else{
					MyApp app = ((MyApp)getApplicationContext());
					app.setUsername(username);
					app.setPassword(password);
					
					PrefUtils.saveToPrefs(LoginActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, username);
					PrefUtils.saveToPrefs(LoginActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, password);
					
					//Voy a la segunda pantalla
					Intent intent = new Intent(this, PrincialActivity.class);
					startActivity(intent);
					finish();
				}
	    	} catch (RuntimeException e){
	    		e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	public void loginView(View view){
		
		EditText usernameEditText = (EditText) findViewById(R.id.usernameField);
	    String username = usernameEditText.getText().toString();
	    
	    EditText passwordEditText = (EditText) findViewById(R.id.passwordField);
	    String password = passwordEditText.getText().toString();
	    
	    this.login(username, password);
	}
}
