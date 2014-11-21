package com.tsi206.jatrik;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tsi206.jatrik.util.PrefUtils;

public class PartidosFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private String username;
	private String password;
	private String apiServer;
	
	public PartidosFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_partidos, container, false);
        
        username = PrefUtils.getFromPrefs(this.getActivity(), PrefUtils.PREFS_LOGIN_USERNAME_KEY, null);
		password = PrefUtils.getFromPrefs(this.getActivity(), PrefUtils.PREFS_LOGIN_PASSWORD_KEY, null);
		MyApp app = ((MyApp)this.getActivity().getApplicationContext());
		apiServer = app.getApiServer();
        
		String opcion = "GetPartidos";
		String urlStringPartidosJugados = apiServer + "getPartidosJugadosManager/" + username + '/' + password;
		String respuestaPartidosJugados = "";
		try {
			respuestaPartidosJugados = new RestAPI(new ProgressDialog(getActivity())).execute(opcion,urlStringPartidosJugados).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonArrayJugados;
		ArrayList<String> arrayPartidos = new ArrayList<String>();
		ArrayList<String> arrayPartidosIds = new ArrayList<String>();
		try {
			jsonArrayJugados = new JSONArray(respuestaPartidosJugados);
			for (int i = 0; i < jsonArrayJugados.length(); i++) {
				JSONObject array_element = (JSONObject) jsonArrayJugados.get(i);
				String fecha = array_element.getString("fecha");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(Long.parseLong(fecha));
				
//				arrayPartidos.add(array_element.getJSONObject("local").getString("nombre")+ " - " 
//						+array_element.getJSONObject("visitante").getString("nombre")+" - "
//						+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
				
				arrayPartidos.add(array_element.getString("nombreLocal")+ " - " 
						+array_element.getString("nombreVisitante")+" - "
						+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
				
				arrayPartidosIds.add(array_element.getString("codigo"));
			}
		    
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListView listPartidosJugados = (ListView) rootView.findViewById(R.id.listPartidosJugados);
		listPartidosJugados.setAdapter(new PartidoArrayAdapter(this.getActivity(), arrayPartidos, arrayPartidosIds));
		//listPartidos.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.list_partidos,arrayPartidos));
		
		listPartidosJugados.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				String idPartido = (String)((TextView)view.findViewById(R.id.idPartido)).getText();
				String labelPartido = (String) ((TextView)view.findViewById(R.id.labelPartido)).getText();
				System.out.println(idPartido + "-->" + labelPartido);
				
				Intent intent = new Intent(parent.getContext(), EstadisticasPartidoActivity.class);
				intent.putExtra("EXTRA_ID_PARTIDO", idPartido);
				intent.putExtra("EXTRA_LABEL_PARTIDO", labelPartido);
				startActivityForResult(intent, 0);
			}
		});
		
		String urlStringPartidosProximos = apiServer + "getProximosPartidosManager/" + username + '/' + password;
		String respuestaPartidosProximos = "";
		
		try {
			respuestaPartidosProximos = new RestAPI(new ProgressDialog(getActivity())).execute(opcion,urlStringPartidosProximos).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonArrayProximos;
		ArrayList<String> arrayPartidosProximos = new ArrayList<String>();
		ArrayList<String> arrayPartidosProximosIds = new ArrayList<String>();
		try {
			jsonArrayProximos = new JSONArray(respuestaPartidosProximos);
			for (int i = 0; i < jsonArrayProximos.length(); i++) {
				JSONObject array_element = (JSONObject) jsonArrayProximos.get(i);
				String fecha = array_element.getString("fecha");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(Long.parseLong(fecha));
				
//				arrayPartidosProximos.add(array_element.getJSONObject("local").getString("nombre")+ " - " 
//						+array_element.getJSONObject("visitante").getString("nombre")+" - "
//						+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
				
				arrayPartidosProximos.add(array_element.getString("nombreLocal")+ " - " 
						+array_element.getString("nombreVisitante")+" - "
						+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
				
				arrayPartidosProximosIds.add(array_element.getString("codigo"));
			}
		    
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListView listPartidosProximos = (ListView) rootView.findViewById(R.id.listProximosPartidos);
		listPartidosProximos.setAdapter(new PartidoArrayAdapter(this.getActivity(), arrayPartidosProximos, arrayPartidosProximosIds));
		//listPartidos.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.list_partidos,arrayPartidos));
		
		listPartidosProximos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				String idPartido = (String)((TextView)view.findViewById(R.id.idPartido)).getText();
				System.out.println(idPartido);
				String labelPartido = (String) ((TextView)view.findViewById(R.id.labelPartido)).getText();
				System.out.println(idPartido + "-->" + labelPartido);
				
				Intent intent = new Intent(parent.getContext(), MinutoAMinutoActivity.class);
				intent.putExtra("EXTRA_ID_PARTIDO", idPartido);
				intent.putExtra("EXTRA_LABEL_PARTIDO", labelPartido);
				startActivityForResult(intent, 0);
			}
		});
		
        return rootView;

    }
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((PrincialActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
	
	public class PartidoArrayAdapter extends ArrayAdapter<String>{
		private final Context context;
		private final ArrayList<String> values;
		private final ArrayList<String> ids;
		
		public PartidoArrayAdapter(Context context, ArrayList<String> arrayPartidos, ArrayList<String> arrayPartidosIds) {
			super(context, R.layout.list_partidos, arrayPartidos);
			this.context = context;
			this.values = arrayPartidos;
			this.ids = arrayPartidosIds;
		}
		
		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View v = inflater.inflate(R.layout.list_partidos, parent, false);
			TextView labelTextView = (TextView) v.findViewById(R.id.labelPartido);
			labelTextView.setText(values.get(position));
			
			TextView idTextView = (TextView) v.findViewById(R.id.idPartido);
			idTextView.setText(ids.get(position));
			
			return v;
		}
	}

}
