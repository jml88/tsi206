package com.tsi206.jatrik;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tsi206.jatrik.util.PrefUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class EstadisticasPartidoActivity extends Activity {

	private String username;
	private String password;
	private String apiServer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadisticas_partido);
		String idPartido = null;
		String labelPartido = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    idPartido = extras.getString("EXTRA_ID_PARTIDO");
		    labelPartido = extras.getString("EXTRA_LABEL_PARTIDO");
		}
		
		TextView labelPartidoEstadisticas = (TextView) findViewById(R.id.labelPartidoEstadisticas);
		labelPartidoEstadisticas.setText(labelPartido);
		
		username = PrefUtils.getFromPrefs(this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		password = PrefUtils.getFromPrefs(this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		MyApp app = ((MyApp)this.getApplicationContext());
		apiServer = app.getApiServer();
		
		int nroComentario = 0;
        
		String opcion = "GetComentariosPartido";
		String urlStringComentariosPartido = apiServer + "getComentariosPartido/" + idPartido + "/" + nroComentario + "/"+ username + '/' + password;
		String respuestaComentariosPartido = "";
		try {
			respuestaComentariosPartido = new RestAPI(new ProgressDialog(this)).execute(opcion, urlStringComentariosPartido).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonArrayComentarios;
		ArrayList<String> arrayComentarios = new ArrayList<String>();
		ArrayList<String> arrayMinutos = new ArrayList<String>();
		try {
			jsonArrayComentarios = new JSONArray(respuestaComentariosPartido);
			for (int i = 0; i < jsonArrayComentarios.length(); i++) {
				JSONObject array_element = (JSONObject) jsonArrayComentarios.get(i);
				arrayComentarios.add(array_element.getString("mensaje"));
				arrayMinutos.add("Minuto " + array_element.getString("minuto") + ": ");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListView comentariosListView = (ListView) this.findViewById(R.id.listEstadisticasPartido);
		comentariosListView.setAdapter(new ComentariosArrayAdapter(this, arrayComentarios, arrayMinutos));
		
		opcion = "GetResultadoPartido";
		String urlStringResultadoPartido = apiServer + "getResultadoPartido/" + idPartido + "/" + username + '/' + password;
		String respuestaResultadoPartido = "";
		try {
			respuestaResultadoPartido = new RestAPI(new ProgressDialog(this)).execute(opcion, urlStringResultadoPartido).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json;
		try {
			json = new JSONObject(respuestaResultadoPartido);
			String golesLocal = json.getString("golesLocal");
			String golesVisitante = json.getString("golesVisitante");
			
			TextView valueResultado = (TextView) findViewById(R.id.valueResultado);
			valueResultado.setText("Local " + golesLocal + " - " + golesVisitante + " Visitante");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadisticas_partido, menu);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch(resultCode)
	    {
	    case 1:
	        setResult(1);
	        finish();
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	public boolean logout(){
		String loggedInUserName = PrefUtils.getFromPrefs(EstadisticasPartidoActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		String loggedInUserPassword = PrefUtils.getFromPrefs(EstadisticasPartidoActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		
		if (loggedInUserName != null & loggedInUserPassword != null){
			PrefUtils.saveToPrefs(EstadisticasPartidoActivity.this, PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
			PrefUtils.saveToPrefs(EstadisticasPartidoActivity.this, PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
			System.out.println("LOGOUT");
			setResult(1);
			finish();
			return true;
		}
		
		return false;
	}
}
