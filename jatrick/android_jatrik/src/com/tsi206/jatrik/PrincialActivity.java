package com.tsi206.jatrik;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsi206.jatrik.util.PrefUtils;

public class PrincialActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.dummy, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
		String loggedInUserName = PrefUtils.getFromPrefs(PrincialActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		String loggedInUserPassword = PrefUtils.getFromPrefs(PrincialActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		
		if (loggedInUserName != null & loggedInUserPassword != null){
			PrefUtils.saveToPrefs(PrincialActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
			PrefUtils.saveToPrefs(PrincialActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
			System.out.println("LOGOUT");
			finish();
			return true;
		}
		
		return false;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private String username;
		private String password;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static Fragment newInstance(int sectionNumber) {
			Fragment fragment = null;
			
			switch (sectionNumber) {
			case 1:
				fragment = new PlaceholderFragment();
				break;
			case 2:
				fragment = new PartidosFragment();
				break;
			case 3:
				fragment = new PartidosFragment();
				break;
			default:
				fragment = new PlaceholderFragment();
				break;
			}
			
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
			
			username = PrefUtils.getFromPrefs(this.getActivity(), PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
			password = PrefUtils.getFromPrefs(this.getActivity(), PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
			
			
			String opcion = "GetEquipo";
			String urlString = "http://192.168.56.1:8080/servicios_jatrik/rest/api/getEquipoManager/" + username + '/' + password;
			String respuesta = "";
			try {
				respuesta = new RestAPI().execute(opcion,urlString).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject json;
			try {
				json = new JSONObject(respuesta);
				String nombreEquipo = json.getString("nombre");
				
				TextView nombreEquipoTextView = (TextView) rootView.findViewById(R.id.nombreEquipoTextView);
			    nombreEquipoTextView.setText(nombreEquipo);
			    
			    JSONObject estadioJSON = json.getJSONObject("estadio");
			    String nombreEstadio = estadioJSON.getString("nombre") + " (" + estadioJSON.getString("capacidad") + ")";
			    
			    TextView nombreEstadioTextView = (TextView) rootView.findViewById(R.id.nombreEstadioValue);
			    nombreEstadioTextView.setText(nombreEstadio);
			    
			    TextView sociosTextView = (TextView) rootView.findViewById(R.id.sociosValue);
			    sociosTextView.setText("Actualmente el club tiene 500 socios");
			    
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((PrincialActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
