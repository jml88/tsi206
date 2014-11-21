package com.tsi206.jatrik;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import com.tsi206.jatrik.util.PrefUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MinutoAMinutoActivity extends Activity implements AsyncDelegate {

	private String username;
	private String password;
	private String apiServer;
	private ArrayList<String> data;
	private ArrayList<String> dataId;
	private int nroComentario;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minuto_a_minuto);
		
		String idPartido = null;
		String labelPartido = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    idPartido = extras.getString("EXTRA_ID_PARTIDO");
		    labelPartido = extras.getString("EXTRA_LABEL_PARTIDO");
		}
		
		TextView labelPartidoEstadisticas = (TextView) findViewById(R.id.labelMinutoAMinuto);
		labelPartidoEstadisticas.setText(labelPartido);
		
		username = PrefUtils.getFromPrefs(this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		password = PrefUtils.getFromPrefs(this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		MyApp app = ((MyApp)this.getApplicationContext());
		apiServer = app.getApiServer();
		
		data = new ArrayList<String>();
		dataId = new ArrayList<String>();
		nroComentario = 0;
		
		ListView minutoAminutoListView = (ListView) this.findViewById(R.id.listMinutoAMinuto);
		adapter = new MinutoAminutoArrayAdapter(this, data, dataId);
		minutoAminutoListView.setAdapter(adapter);
		
		String urlStringComentariosPartido = apiServer + "getComentariosPartido/" + idPartido + "/" + nroComentario + "/"+ username + '/' + password;
		
		TimerTask updateList = new MyTimer(this, data, dataId, urlStringComentariosPartido);
		updateList.run();		
		
		Timer t = new Timer("minutoAminutoTimer", true);
		t.scheduleAtFixedRate(updateList, 20000, 20000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.minuto_aminuto, menu);
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
	
	@Override
	public void asyncComplete(boolean success){
		adapter.notifyDataSetInvalidated();
	    adapter.notifyDataSetChanged();
	}
	
}
