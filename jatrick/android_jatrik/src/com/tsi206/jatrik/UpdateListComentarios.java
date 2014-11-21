package com.tsi206.jatrik;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class UpdateListComentarios extends AsyncTask<Object, Object, Object> {

	private AsyncDelegate delegate;
	
	public UpdateListComentarios(AsyncDelegate delegate) {
		this.delegate = delegate;
	}
	
	@Override
    protected String doInBackground(Object... params) {
		
		String urlString = (String) params[0];
		ArrayList<String> data = (ArrayList<String>) params[1];
		ArrayList<String> dataId = (ArrayList<String>) params[2];
		
		data.clear();
		dataId.clear();
		
		String nroComentario = (String) params[3];
		
		String result = "";	    
	    HttpClient httpclient = new DefaultHttpClient();  
	    HttpGet request = new HttpGet(urlString);
	    
	    try {
			//HttpResponse response = httpclient.execute(request);
		    ResponseHandler<String> handler = new BasicResponseHandler();
		    result =  httpclient.execute(request, handler);			
		    System.out.println(result);
		    
		    System.out.println("Numero de comentario antes --> " + nroComentario);
		    
		    JSONArray jsonArrayComentarios;
			try {
				jsonArrayComentarios = new JSONArray(result);
				for (int i = 0; i < jsonArrayComentarios.length(); i++) {
					JSONObject array_element = (JSONObject) jsonArrayComentarios.get(i);
					data.add(array_element.getString("mensaje"));
					dataId.add("Minuto " + array_element.getString("minuto") + ": ");
					nroComentario = array_element.getString("codigo");										
				}
				
				System.out.println("Numero de comentario --> " + nroComentario);
				return nroComentario;
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}
		    
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}    
	}
	
	@Override
	protected void onPostExecute(Object result) {
		delegate.asyncComplete(true);
    }
}
